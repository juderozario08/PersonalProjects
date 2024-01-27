from PIL import Image
import pytesseract
from os import getcwd, listdir
from pprint import pprint
from datetime import date as dt

PATH = '/Users/juderozario/Downloads/images/'
EXTRACTED_IMAGE = ''
EXTRACTED_TEXT = []
DAYS = ['Sunday',
        'Monday',
        'Tuesday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday']
SHIFT_MONTHS = {1: [], 2: [], 3: [], 4: [], 5: [], 6: [], 7: [], 8: [], 9: [], 10: [], 11: [], 12: []}
TODAY = str(dt.today())
CURRENT_DATE = int(TODAY[8:])
CURRENT_MONTH = int(TODAY[5:7])
CURRENT_YEAR = int(TODAY[:4])


class Shift:
    def __init__(self, year: int, month: int, date: int, start_hour: int, start_minute: int, end_hour: int,
                 end_minute: int, role: str):
        self.year = year
        self.month = month
        self.date = date
        self.start_hour = start_hour
        self.start_minute = start_minute
        self.end_hour = end_hour
        self.end_minute = end_minute
        self.role = role

    def get_year(self):
        return self.year

    def get_month(self):
        return self.month

    def get_date(self):
        return self.date

    def get_start_hour(self):
        return self.start_hour

    def get_start_minute(self):
        return self.start_minute

    def get_end_hour(self):
        return self.end_hour

    def get_end_minute(self):
        return self.end_minute

    def get_role(self):
        return self.role


def get_text_from_picture():
    global EXTRACTED_IMAGE, EXTRACTED_TEXT
    for image in listdir(PATH):
        if image.endswith('.png'):
            image_path = PATH + image
            img = Image.open(image_path)
            EXTRACTED_IMAGE += pytesseract.image_to_string(img).replace('\r', '').replace(' ', '').replace('.', '')
    EXTRACTED_TEXT = EXTRACTED_IMAGE.split('\n')
    try:
        while True:
            EXTRACTED_TEXT.remove('')
    except ValueError:
        pass


def get_shifts_from_text():
    get_text_from_picture()
    data = []
    for i in range(len(EXTRACTED_TEXT)):
        for day in DAYS:
            if day in EXTRACTED_TEXT[i]:
                if '/' in EXTRACTED_TEXT[i + 2]:
                    date = EXTRACTED_TEXT[i].replace('.', '')[:EXTRACTED_TEXT[i].find(day)]
                    Date = ''
                    for j in date:
                        if j.isdigit():
                            Date += j
                    if Date:
                        time = EXTRACTED_TEXT[i + 1][:EXTRACTED_TEXT[i + 1].find('[')]
                        role = EXTRACTED_TEXT[i + 2][EXTRACTED_TEXT[i + 2].find('/') + 1:]
                        data.append([Date, time, role])
                break
    return format_data(data)


def extract_time(Time: str) -> list[int]:
    time = Time.split('-')
    for i in range(len(time)):
        time[i] = time[i].split(':')
    if 'PM' in time[0][1]:
        time[0][0] = int(time[0][0]) + 12 if int(time[0][0]) < 12 else int(time[0][0])
    if 'PM' in time[1][1]:
        time[1][0] = int(time[1][0]) + 12 if int(time[1][0]) < 12 else int(time[1][0])
    start_hour = int(time[0][0])
    start_minute = int(time[0][1][:2])
    end_hour = int(time[1][0])
    end_minute = int(time[1][1][:2])
    return [start_hour, start_minute, end_hour, end_minute]


def check_change_for_month(data) -> int:
    if 'January' in data:
        return 1
    elif 'February' in data:
        return 2
    elif 'March' in data:
        return 3
    elif 'April' in data:
        return 4
    elif 'May' in data:
        return 5
    elif 'June' in data:
        return 6
    elif 'July' in data:
        return 7
    elif 'August' in data:
        return 8
    elif 'September' in data:
        return 9
    elif 'October' in data:
        return 10
    elif 'November' in data:
        return 11
    elif 'December' in data:
        return 12
    else:
        return -1


def format_data(data: list[list[str]]) -> list[Shift]:
    shifts = []
    for line in data:
        date = int(line[0])
        month = CURRENT_MONTH
        year = CURRENT_YEAR
        time = extract_time(line[1])
        role = line[2]
        if CURRENT_MONTH == 12 and date < CURRENT_DATE:
            year = CURRENT_YEAR + 1
            month = 1
        elif CURRENT_DATE > date:
            month = CURRENT_MONTH + 1
        shifts.append(Shift(year, month, date, time[0], time[1], time[2], time[3], role))
    return shifts

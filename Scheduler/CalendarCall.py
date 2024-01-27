from pprint import pprint
from Google import Create_Service, convert_to_RFC_datetime
from DataProcessor import get_shifts_from_text

CLIENT_SECRET_FILE = 'credentials.json'
API_NAME = 'calendar'
API_VERSION = 'v3'
SCOPES = ['https://www.googleapis.com/auth/calendar']
LOCATION = '517 Richmond Street East, Toronto, ON M5A 1R4'
service = Create_Service(CLIENT_SECRET_FILE, API_NAME, API_VERSION, SCOPES)

work_calendar_id = 'iqum5089gg20ev7s3vdo05pqfg@group.calendar.google.com'

"""
To create a calendar
"""
# response = service.calendars().insert(body=request_body).execute()
# response = service.calendars().get(calendarId=response['id']).execute()
# pprint(response)

"""
To delete a calendar
"""
# service.calendars().delete(calendarId='e59nsq71r37c41esg68ved93cg@group.calendar.google.com').execute()

"""
To list calendars
"""
# response = service.calendarList().list().execute()
# calendarItems = response.get('items')
# nextPageToken = response.get('nextPageToken')

# while nextPageToken:
#     response = service.calendarList().list().execute()
#     calendarItems.extend(response.get('items'))
#     nextPageToken = response.get('nextPageToken')
#
# pprint(calendarItems)
"""
Create an event
"""
colors = service.colors().get().execute()
for shift in get_shifts_from_text():
    event_request_body = { 
        'start': {
            'dateTime': convert_to_RFC_datetime(shift.get_year(), shift.get_month(), shift.get_date(),
                                                shift.get_start_hour(), shift.get_start_minute())[:19] + '-04:00',
        },
        'end': {
            'dateTime': convert_to_RFC_datetime(shift.get_year(), shift.get_month(), shift.get_date(),
                                                shift.get_end_hour(), shift.get_end_minute())[:19] + '-04:00',
        },
        'summary': 'Work',
        'description': shift.get_role(),
        'colorId': 11,
        'status': 'confirmed',
        'location': LOCATION,
        'reminders': {
            'useDefault': False,
            'overrides': [
                {'method': 'popup', 'minutes': 60*14}
            ]
        }
    }
    send_notification = True
    send_update = 'none'
    service.events().insert(
        calendarId=work_calendar_id,
        sendUpdates=send_update,
        sendNotifications=send_notification,
        body=event_request_body).execute()

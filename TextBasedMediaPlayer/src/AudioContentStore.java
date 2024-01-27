// Student Name: Jude Rozario
// Student ID: 501166063
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
	private final ArrayList<AudioContent> contents;
	private final HashMap<String, ArrayList<Integer>> artistContents;
	private final HashMap<String, ArrayList<Integer>> titleContents;
	private final HashMap<Song.Genre, ArrayList<Integer>> genreContents;
	private final HashMap<String, ArrayList<Integer>> generalMap;

	public AudioContentStore() {
		contents = new ArrayList<AudioContent>();
		artistContents = new HashMap<>();
		genreContents = new HashMap<>();
		titleContents =  new HashMap<>();
		generalMap = new HashMap<>();
		try {
			// This method reads the file and stores every content in the contents arraylist
			readFile(new File("store.txt"));
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
	}
	private void reader(String str, int index){
		// Add the given string to the generalMap
		addToMap(generalMap, str.toLowerCase(), index);
	}
	private void addToMap(HashMap<String, ArrayList<Integer>> map, String name, int index) {
		ArrayList<Integer> values;
		if (map.containsKey(name)){
			values = map.get(name);
			if (!values.contains(index))
				values.add(index);
		}
		else{
			values = new ArrayList<>();
			values.add(index);
			map.put(name, values);
		}
	}
	private void addToGenreMap(HashMap<Song.Genre, ArrayList<Integer>> map, Song.Genre genre, int index){
		ArrayList<Integer> values;
		if (map.containsKey(genre)){
			values = map.get(genre);
			if (!values.contains(index))
				values.add(index);
		}
		else{
			values = new ArrayList<Integer>();
			values.add(index);
			map.put(genre, values);
		}
	}
	private void readFile(File file) throws FileNotFoundException {
		// Create a scanner that reads the file and uses the delimiter " " to separate groups of characters
		Scanner scanner = new Scanner(file);
		int index = 1; // This is to track the indexes of the audio content
		while (scanner.hasNextLine()) {
			String id, title, author, narrator, artist, composer, audFile, yearS, lengthS;
			int year, length;
			String type = scanner.nextLine(); // Get the type of audio content
			reader(type, index); // This is method is to create the HashMap for the partial string search
			switch (type) {
				case "SONG" -> {
					// The next few sets of lines are just to get the values from the file and store in their respective variables
					id = scanner.nextLine();
					reader(id, index);
					title = scanner.nextLine();
					reader(title, index);
					addToMap(titleContents, title.trim().toLowerCase(), index);// Adds to the title hashmap
					yearS = scanner.nextLine();
					reader(yearS, index);
					year = Integer.parseInt(yearS);
					lengthS = scanner.nextLine();
					reader(lengthS, index);
					length = Integer.parseInt(lengthS);
					artist = scanner.nextLine();
					reader(artist, index);
					addToMap(artistContents, artist.trim().toLowerCase(), index); // Adds to the artist hashmap
					composer = scanner.nextLine();
					reader(composer, index);
					String genreS = scanner.nextLine();
					reader(genreS, index);
					Song.Genre genre = Song.Genre.valueOf(genreS);
					addToGenreMap(genreContents, genre, index); // Adds to the genre hash map
					audFile = "";
					// Based on the number of lines of the lyrics, loop through and create the lyrics
					int lines = Integer.parseInt(scanner.nextLine());
					for (int i = 0; i < lines; i++) {
						String str = scanner.nextLine();
						reader(str, index);
						audFile += str + "\n";
					}
					// Create a new Song and append it to the contents array list
					contents.add(new Song(title, year, id, Song.TYPENAME, audFile, length, artist, composer, genre, audFile));
				}
				// Repeats similar steps to songs
				case "AUDIOBOOK" -> {
					id = scanner.nextLine();
					reader(id, index);
					title = scanner.nextLine();
					reader(title, index);
					addToMap(titleContents, title.trim().toLowerCase(), index);
					yearS = scanner.nextLine();
					reader(yearS, index);
					year = Integer.parseInt(yearS);
					lengthS = scanner.nextLine();
					reader(lengthS, index);
					length = Integer.parseInt(lengthS);
					author = scanner.nextLine();
					reader(author, index);
					addToMap(artistContents, author.trim().toLowerCase(), index);
					narrator = scanner.nextLine();
					reader(narrator, index);
					ArrayList<String> chapterTitles = new ArrayList<String>();
					audFile = "";
					int numOfChapters = Integer.parseInt(scanner.nextLine());
					for (int i = 0; i < numOfChapters; i++) {
						String chapterTitle = scanner.nextLine();
						reader(chapterTitle, index);
						chapterTitles.add(chapterTitle);
					}
					ArrayList<String> chapters = new ArrayList<String>();
					for (int i = 0; i < numOfChapters; i++) {
						String chapter = "";
						int line = Integer.parseInt(scanner.nextLine());
						for (int j = 0; j < line; j++) {
							String chapterLine = scanner.nextLine();
							reader(chapter, index);
							chapter += chapterLine + "\n";
						}
						chapters.add(chapter);
					}
					// Add the audiobook contents arraylist
					contents.add(new AudioBook(title, year, id, AudioBook.TYPENAME, audFile, length, author, narrator, chapterTitles, chapters));
				}
				default -> System.out.println("Type not found");
			}
			index++; // Increment the index to keep track of the indexes
		}
	}
	public ArrayList<AudioContent> getContents() {
		return this.contents;
	}
	public HashMap<String, ArrayList<Integer>> getGeneralMap() {
		return generalMap;
	}
	public HashMap<Song.Genre, ArrayList<Integer>> getGenreContents() {
		return this.genreContents;
	}
	public HashMap<String, ArrayList<Integer>> getArtistContents() {
		return this.artistContents;
	}
	public HashMap<String, ArrayList<Integer>> getTitleContents(){
		return this.titleContents;
	}

    public void listAll() {
		int index = 1;
		for (AudioContent content : contents){
			System.out.print(index + ". ");
			if (content.getType().equals("SONG"))
				((Song)content).printInfo();
			else if (content.getType().equals("AUDIOBOOK"))
				((AudioBook)content).printInfo();
			index ++;
		}
    }
}

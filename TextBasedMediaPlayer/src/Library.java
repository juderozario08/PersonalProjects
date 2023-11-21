// Student Name: Jude Rozario
// Student ID: 501166063
import java.util.*;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private final ArrayList<Song> songs;
	private final ArrayList<AudioBook> audiobooks;
	private final ArrayList<Playlist> playlists;

	public Library()
	{
		songs = new ArrayList<>();
		audiobooks = new ArrayList<>();
		playlists = new ArrayList<>();
	}

	public void download(ArrayList<AudioContent> contents, int from, int to) {
		if (from < 0 || from > contents.size() || to < 0 || to > contents.size() || from > to)
			throw new InvalidContentRangeException("Please enter a range between 1 to "+contents.size());
		for (int i = from - 1; i < to; i++){
			switch (contents.get(i).getType()) {
				case "SONG" -> {
					Song song = (Song) contents.get(i);
					if (!songs.contains(song)) {
						songs.add(song);
						System.out.println(song.getType() + " " + song.getTitle() + " Added to library");
					} else System.out.println(song.getType() + " " + song.getTitle() + " Already in library");
				}
				case "AUDIOBOOK" -> {
					AudioBook book = (AudioBook) contents.get(i);
					if (!audiobooks.contains(book)) {
						audiobooks.add(book);
						System.out.println(book.getType() + " " + book.getTitle() + " Added to library");
					} else System.out.println(book.getType() + " " + book.getTitle() + " Already in library");
				}
				default -> throw new TypeNotFoundException("Content Type not found");
			}
		}
	}
	public void downloada(ArrayList<AudioContent> contents, HashMap<String, ArrayList<Integer>> map, String artist){
		if (!map.containsKey(artist.trim().toLowerCase()))
			throw new CreatorNotFoundException("The Creator does not exist");
		for (int index : map.get(artist.trim().toLowerCase())){
			switch (contents.get(index - 1).getType()) {
				case "SONG", "AUDIOBOOK" -> download(contents, index, index);
				default -> throw new TypeNotFoundException("This type of audio content does not exist");
			}
		}
	}
	public void downloadg(ArrayList<AudioContent> contents, HashMap<Song.Genre, ArrayList<Integer>> map, Song.Genre genre){
		if (!map.containsKey(genre))
			throw new GenreNotFoundException("The Genre does not exist");
		for (int index : map.get(genre)){
			download(contents, index, index);
		}
	}
	public void search(ArrayList<AudioContent> contents, HashMap<String, ArrayList<Integer>> map, String title){
		if (!map.containsKey(title.trim().toLowerCase()))
			throw new TitleNotFoundException("The Title does not exist");
		for (int index : map.get(title.trim().toLowerCase())){
			switch (contents.get(index - 1).getType()) {
				case "SONG" -> {
					System.out.print(index + ". ");
					Song song = (Song)contents.get(index - 1);
					song.printInfo();
				}
				case "AUDIOBOOK" -> {
					System.out.print(index + ". ");
					AudioBook book = (AudioBook)contents.get(index - 1);
					book.printInfo();
				}
			}
		}
	}
	public void searcha(ArrayList<AudioContent> contents, HashMap<String, ArrayList<Integer>> map, String artist){
		if (!map.containsKey(artist.trim().toLowerCase()))
			throw new CreatorNotFoundException("The Creator does not exist");
		for (int index : map.get(artist.trim().toLowerCase())){
			switch (contents.get(index - 1).getType()) {
				case "SONG" -> {
					System.out.print(index + ". ");
					Song song = (Song)contents.get(index - 1);
					song.printInfo();
				}
				case "AUDIOBOOK" -> {
					System.out.print(index + ". ");
					AudioBook book = (AudioBook)contents.get(index - 1);
					book.printInfo();
				}
				default -> throw new TypeNotFoundException("This type does not exist");
			}
		}
	}
	public void searchg(ArrayList<AudioContent> contents, HashMap<Song.Genre, ArrayList<Integer>> map, Song.Genre genre){
		if (!map.containsKey(genre))
			throw new GenreNotFoundException("A content with this genre does not exist");
		for (int index : map.get(genre)){
			System.out.print(index + ". ");
			contents.get(index - 1).printInfo();
		}
	}
	public void searchp(ArrayList<AudioContent> contents, HashMap<String, ArrayList<Integer>> map, String target){
		ArrayList<Integer> possibleIndices = new ArrayList<>();
		Set<String> targets = map.keySet();
		for (String key : targets) {
			if (key.contains(target)) {
				for (int index : map.get(key)) {
					if (!possibleIndices.contains(index))
						possibleIndices.add(index);
				}
			}
		}
		if (possibleIndices.size()==0)
			throw new TargetNotFoundException("The target string was not found");
		Collections.sort(possibleIndices);
		for (int i : possibleIndices){
			System.out.print(i + ". ");
			contents.get(i-1).printInfo();
		}
	}

	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs() {
		for (int i = 0; i < songs.size(); i++) {
			int index = i + 1;
			System.out.print(index + ". ");
			songs.get(i).printInfo();
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks() {
		int index = 1;
		for (AudioBook book : audiobooks){
			System.out.print(index + ". ");
			book.printInfo();
			index++;
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts() {

	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number in listAllSongs() above
	public void listAllPlaylists() {
		int index = 1;
		for (Playlist playlist : playlists){
			System.out.print(index + ". " + playlist.getTitle());
			System.out.println();
			index++;
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arraylist is complete, print the artists names
		int index = 1;
		for (Song song : songs){
			System.out.println(index + ". " + song.getArtist());
			index ++;
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)
	{
		if (songs.isEmpty())
			throw new ContentListEmptyException("No songs to delete");
		else if (!containsIndex(index, songs.size()))
			throw new InvalidContentRangeException("The range of the songs must be between 1 and " + songs.size());
		for (Playlist playlist : playlists){
			for (AudioContent content : playlist.getContent()){
				if (content.getType().equals("SONG")){
					Song song = (Song) content;
					if (song.equals(songs.get(index-1)))
						playlist.getContent().remove(song);
				}
			}
		}
		songs.remove(index-1);
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		songs.sort(new SongYearComparator());
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private static class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song songa, Song songb){
			return songa.getYear() - songb.getYear();
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
		songs.sort(new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private static class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song songa, Song songb){
			return songa.getLength() - songb.getLength();
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{
		if (!containsIndex(index, songs.size()))
			throw new InvalidContentRangeException("Index not valid. Select an index between 1 and " + songs.size());
		else songs.get(index-1).play();
	}
	
	// Play a chapter of an audiobook from list of audiobooks
	public void playAudioBook(int index, int chapter)
	{
		if (index < 1 || index > audiobooks.size())
			throw new InvalidContentRangeException("The range of the Audiobooks must be between 1 and " + audiobooks.size());
		else if (chapter < 1 || chapter > audiobooks.get(index-1).getChapterTitles().size())
			throw new InvalidContentRangeException("The range of the chapters in the Audiobook must be between 1 and " + audiobooks.get(index-1).getChapterTitles().size());
		else {
			audiobooks.get(index-1).selectChapter(chapter);
			audiobooks.get(index - 1).play();
		}
	}
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)
	{
		if (audiobooks.isEmpty())
			throw new ContentListEmptyException("There are no current audiobooks that exist");
		else if (index < 1 || index > audiobooks.size())
			throw new InvalidContentRangeException("The range of the content must be between 1 and " + audiobooks.size());
		for (int i = 0; i < audiobooks.get(index-1).getChapterTitles().size(); i++)
			System.out.println((i+1) + ". " + audiobooks.get(index-1).getChapterTitles().get(i));
	}
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		if (playlistContainsTitle(title))
			throw new PlaylistAlreadyExistsException("The playlist with the same title already exists");
		else playlists.add(new Playlist(title));
	}

	private boolean playlistContainsTitle(String title)
	{
		for (Playlist playlist : playlists){
			if (playlist.getTitle().equals(title))
				return true;
		}
		return false;
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{
		if (playlists.isEmpty())
			throw new PlaylistEmptyException("There are no playlists that currently exist");
		else if (!playlistContainsTitle(title))
			throw new PlaylistNotFoundException("A playlist with this title does not currently exist");
		else{
			for (Playlist playlist : playlists){
				if (playlist.getTitle().equals(title)){
					if (playlist.getContent().isEmpty())
						throw new PlaylistEmptyException("The playlist is currently empty");
					int count = 1;
					for (AudioContent content : playlist.getContent()){
						switch (content.getType()) {
							case "SONG" -> {
								Song song = (Song) content;
								System.out.print(count + ". ");
								song.printInfo();
							}
							case "AUDIOBOOK" -> {
								AudioBook book = (AudioBook) content;
								System.out.print(count + ". ");
								book.printInfo();
							}
							default -> throw new TypeNotFoundException(content.getType() + " is not supported.");
						}
						count++;
					}
				}
			}
		}
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)
	{
		if (playlists.isEmpty())
			throw new PlaylistEmptyException("There are no playlists that currently exist");
		else if (!playlistContainsTitle(playlistTitle))
			throw new PlaylistNotFoundException("A playlist with this title does not currently exist");
		for (Playlist playlist : playlists) {
			if (playlist.getTitle().equals(playlistTitle)) {
				if (playlist.getContent() == null)
					throw new PlaylistEmptyException("There are no contents in the current playlist");
				for (int j = 0; j < playlist.getContent().size(); j++) {
					switch (playlist.getContent(j + 1).getType()) {
						case "SONG" -> {
							Song song = (Song) playlist.getContent(j + 1);
							song.play();
						}
						case "AUDIOBOOK" -> {
							AudioBook book = (AudioBook) playlist.getContent(j + 1);
							for (int k = 0; k < book.getChapterTitles().size(); k++) {
								book.selectChapter(k + 1);
								book.play();
							}
							System.out.println();
						}
					}
				}
			}
		}
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
	{
		if (playlists.isEmpty())
			throw new PlaylistEmptyException("There are no playlists that currently exist");
		else if (!playlistContainsTitle(playlistTitle))
			throw new PlaylistNotFoundException("A playlist with this title does not currently exist");
		else{
			for (Playlist playlist : playlists) {
				if (playlist.getTitle().equals(playlistTitle)) {
					if (playlist.getContent() == null)
						throw new PlaylistEmptyException("There are no contents in the current playlist");
					else if (!containsIndex(indexInPL, playlist.getContent().size()))
						throw new InvalidContentRangeException("The index must be between 1 and " + playlist.getContent().size());
					switch (playlist.getContent(indexInPL).getType()) {
						case "SONG" -> {
							Song song = (Song) playlist.getContent(indexInPL);
							song.play();
						}
						case "AUDIOBOOK" -> {
							AudioBook book = (AudioBook) playlist.getContent(indexInPL);
							for (int j = 0; j < book.getChapterTitles().size(); j++) {
								book.selectChapter(j + 1);
								book.play();
							}
						}
						default -> throw new TypeNotFoundException("Content Type does not exist");
					}
				}
			}
		}
	}

	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		if (playlists.isEmpty())
			throw new PlaylistEmptyException("There are no playlists that currently exist");
		else if (!playlistContainsTitle(playlistTitle))
			throw new PlaylistNotFoundException("A playlist with this title does not currently exist");
		for (Playlist playlist : playlists){
			if (playlist.getTitle().equals(playlistTitle)){
				switch (type) {
					case "SONG" -> {
						if (songs.isEmpty())
							throw new ContentListEmptyException("There are no songs that currently exist");
						else if (!containsIndex(index, songs.size()))
							throw new InvalidContentRangeException("The range of the AudioContent must be between 1 and " + songs.size());
						else {
							for (AudioContent content : playlist.getContent()) {
								Song song = (Song) content;
								if (content.getType().equals("SONG") && song.equals(songs.get(index - 1)))
									throw new PlaylistAlreadyContainsContentException("The Song already exists in the playlist");
							}
							playlist.getContent().add(songs.get(index - 1));
						}
					}
					case "AUDIOBOOK" -> {
						if (audiobooks.isEmpty())
							throw new ContentListEmptyException("There are no audiobooks that currently exist");
						else if (!containsIndex(index, audiobooks.size()))
							throw new InvalidContentRangeException("The range of the AudioContent must be between 1 and " + audiobooks.size());
						else {
							for (AudioContent content : playlist.getContent()) {
								if (content.getType().equals("AUDIOBOOK") && ((AudioBook) content).equals(audiobooks.get(index - 1)))
									throw new PlaylistAlreadyContainsContentException("The AudioBook already exists in the playlist");
							}
							playlist.getContent().add(audiobooks.get(index - 1));
						}
					}
					default -> throw new TypeNotFoundException("This type of AudioContent is not supported");
				}
			}
		}
	}
	private boolean containsIndex(int index, int size)
	{
		return (index >= 1 || index <= size);
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title)
	{
		if (playlists.isEmpty())
			throw new PlaylistEmptyException("The playlist list is empty");
		for (Playlist playlist : playlists) {
			if (playlist.getTitle().equals(title)) {
				if (!containsIndex(index, playlist.getContent().size()))
					throw new InvalidContentRangeException("Content range must be between 1 and " + playlist.getContent().size());
				playlist.getContent().remove(index - 1);
				return;
			}
		}
		throw new PlaylistNotFoundException("The playlist does not exist");
	}

	public static class InvalidContentRangeException extends RuntimeException {
		public InvalidContentRangeException(String message) {
			super(message);
		}
	}
	public static class TypeNotFoundException extends RuntimeException {
		public TypeNotFoundException(String message){
			super(message);
		}
	}
	public static class CreatorNotFoundException extends RuntimeException {
		public CreatorNotFoundException(String message){
			super(message);
		}
	}
	public static class GenreNotFoundException extends RuntimeException {
		public GenreNotFoundException(String message){
			super(message);
		}
	}
	public static class TitleNotFoundException extends RuntimeException {
		public TitleNotFoundException(String message){
			super(message);
		}
	}
	public static class ContentListEmptyException extends RuntimeException {
		public ContentListEmptyException(String message){
			super(message);
		}
	}
	public static class PlaylistAlreadyExistsException extends RuntimeException{
		public PlaylistAlreadyExistsException(String message){
			super(message);
		}
	}
	public static class PlaylistEmptyException extends RuntimeException{
		public PlaylistEmptyException(String message){
			super(message);
		}
	}
	public static class PlaylistNotFoundException extends RuntimeException{
		public PlaylistNotFoundException(String message){
			super(message);
		}
	}
	public static class PlaylistAlreadyContainsContentException extends RuntimeException{
		public PlaylistAlreadyContainsContentException(String message){
			super(message);
		}
	}
	public static class TargetNotFoundException extends RuntimeException{
		public TargetNotFoundException(String message){
			super(message);
		}
	}
}


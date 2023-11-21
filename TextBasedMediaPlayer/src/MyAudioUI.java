// Student Name: Jude Rozario
// Student ID: 501166063
import java.util.Scanner;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			else if (action.equalsIgnoreCase("DOWNLOAD"))
			{
				int indexFrom = 0;
				int indexTo = 0;
				System.out.print("From Content #: ");
				if (scanner.hasNextInt())
				{
					indexFrom = scanner.nextInt();
					scanner.nextLine();
				}
				System.out.print("To Content #: ");
				if (scanner.hasNextInt())
				{
					indexTo = scanner.nextInt();
					scanner.nextLine();
				}
				try{
					mylibrary.download(store.getContents(), indexFrom, indexTo);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("DOWNLOADA"))
			{
				String artist = "";
				System.out.print("Enter Artist Name: ");
				if (scanner.hasNext()){
					artist = scanner.nextLine();
				}
				try{
					mylibrary.downloada(store.getContents(), store.getArtistContents(), artist);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("DOWNLOADG"))
			{
				String genre = "";
				System.out.print("Enter a Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				if (scanner.hasNext()){
					genre = scanner.next();
					scanner.nextLine();
				}
				try{
					mylibrary.downloadg(store.getContents(), store.getGenreContents(), Song.Genre.valueOf(genre));
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SEARCH"))
			{
				String title = "";
				System.out.print("Enter Title: ");
				if (scanner.hasNext()){
					title = scanner.nextLine();
				}
				try{
					mylibrary.search(store.getContents(), store.getTitleContents(), title);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SEARCHA"))
			{
				String artist = "";
				System.out.print("Enter Artist Name: ");
				if (scanner.hasNext()){
					artist = scanner.nextLine();
				}
				try{
					mylibrary.searcha(store.getContents(), store.getArtistContents(), artist);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SEARCHG"))
			{
				String genre = "";
				System.out.print("Enter a Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				if (scanner.hasNext()){
					genre = scanner.next().toUpperCase();
					scanner.nextLine();
				}
				try{
					mylibrary.searchg(store.getContents(), store.getGenreContents(), Song.Genre.valueOf(genre));
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SEARCHP"))
			{
				String target = "";
				System.out.print("Enter Target String: ");
				if (scanner.hasNext()){
					target = scanner.nextLine();
				}
				try{
					mylibrary.searchp(store.getContents(), store.getGeneralMap(), target.toLowerCase());
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("PLAYSONG"))
			{
				// Print error message if the song doesn't exist in the library
				int index = 0;
				System.out.print("Enter Song #: ");
				if (scanner.hasNextInt()){
					index = scanner.nextInt();
					scanner.nextLine();
				}
				try{
					mylibrary.playSong(index);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("BOOKTOC"))
			{
				int index = 0;
				System.out.print("Enter Book #: ");
				if (scanner.hasNextInt()){
					index = scanner.nextInt();
					scanner.nextLine();
				}
				try{
					mylibrary.printAudioBookTOC(index);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("PLAYBOOK"))
			{
				int index = 0;
				System.out.print("Enter AudioBook #: ");
				if (scanner.hasNextInt()){
					index = scanner.nextInt();
					scanner.nextLine();
				}
				int chapter = 0;
				System.out.print("Enter AudioBook Chapter #: ");
				if (scanner.hasNextInt()){
					chapter = scanner.nextInt();
					scanner.nextLine();
				}
				try{
					mylibrary.playAudioBook(index, chapter);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
//			else if (action.equalsIgnoreCase("PODTOC"))
//			{
//				int index = 0;
//				int season = 0;
//
//				System.out.print("Podcast Number: ");
//				if (scanner.hasNextInt())
//				{
//					index = scanner.nextInt();
//				}
//				System.out.print("Season: ");
//				if (scanner.hasNextInt())
//				{
//					season = scanner.nextInt();
//					scanner.nextLine();
//				}
////				if (!mylibrary.printPodcastEpisodes(index, season))
////					System.out.println(mylibrary.getErrorMessage());
//			}
//			else if (action.equalsIgnoreCase("PLAYPOD"))
//			{
//				int index = 0;
//
//				System.out.print("Podcast Number: ");
//				if (scanner.hasNextInt())
//				{
//					index = scanner.nextInt();
//					scanner.nextLine();
//				}
//				int season = 0;
//				System.out.print("Season: ");
//				if (scanner.hasNextInt())
//				{
//					season = scanner.nextInt();
//					scanner.nextLine();
//				}
//				int episode = 0;
//				System.out.print("Episode: ");
//				if (scanner.hasNextInt())
//				{
//					episode = scanner.nextInt();
//					scanner.nextLine();
//				}
////				if (!library.playPodcast(index, season, episode))
////					System.out.println(library.getErrorMessage());
//			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				String title = "";
				System.out.print("Enter Title of Playlist: ");
				if (scanner.hasNext()){
					title = scanner.nextLine();
				}
				try{
					mylibrary.playPlaylist(title);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				String title = "";
				System.out.print("Enter Title of Playlist: ");
				if (scanner.hasNext()){
					title = scanner.nextLine();
				}
				int index = 0;
				System.out.print("Enter the index of the content: ");
				if (scanner.hasNextInt()){
					index = scanner.nextInt();
					scanner.nextLine();
				}
				try{
					mylibrary.playPlaylist(title, index);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int index = 0;
				System.out.print("Enter the index of the content: ");
				if (scanner.hasNextInt()){
					index = scanner.nextInt();
					scanner.nextLine();
				}
				try{
					mylibrary.deleteSong(index);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String title = "";
				System.out.print("Enter title of the playlist: ");
				if (scanner.hasNext()){
					title = scanner.nextLine();
				}
				try{
					mylibrary.makePlaylist(title);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				String title = "";
				System.out.print("Enter title of the playlist: ");
				if (scanner.hasNext()){
					title = scanner.nextLine();
				}
				try{
					mylibrary.printPlaylist(title);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				String type = "";
				System.out.print("Select Type [SONG/AUDIOBOOK]: ");
				if (scanner.hasNext()){
					type = scanner.nextLine().toUpperCase();
				}
				int index = 0;
				System.out.print("Enter index of content: ");
				if (scanner.hasNextInt()){
					index = scanner.nextInt();
					scanner.nextLine();
				}
				String title = "";
				System.out.print("Enter title of playlist: ");
				if (scanner.hasNext()){
					title = scanner.nextLine();
				}
				try{
					mylibrary.addContentToPlaylist(type, index, title);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				String title = "";
				System.out.print("Enter title of playlist: ");
				if (scanner.hasNext()){
					title = scanner.nextLine();
				}
				int index = 0;
				System.out.print("Enter index of content: ");
				if (scanner.hasNextInt()){
					index = scanner.nextInt();
					scanner.nextLine();
				}
				try{
					mylibrary.delContentFromPlaylist(index, title);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}

			System.out.print("\n>");
		}
	}
}

package vknyazev_B20_A03_MusicSystem;

/**
 * MusicSystem
 */
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class MusicSystem {

    private ArrayList<Song> songs = new ArrayList<Song>();
    private Scanner sc;
    private File sf;

    public MusicSystem() throws IOException, FileNotFoundException{
        sc = new Scanner(sf);
        sc.useDelimiter("*");
        while (sc.hasNext()) {
            int year = sc.nextInt();
            int ranking = sc.nextInt();
            String artist = sc.next();
            String title = sc.next();
            int downloadCount = sc.nextInt();
            Song song = new Song(year, ranking, artist, title, downloadCount);
            songs.add(song);
        }
    }

    /**
     * @return the songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * @param songs the songs to set
     */
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void sortByYear() {
        // TODO: add sorting
    }
    
    public void sortByRank() {
        // TODO: add sorting
    }
}
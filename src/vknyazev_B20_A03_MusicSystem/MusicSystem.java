package vknyazev_B20_A03_MusicSystem;

/**
 * MusicSystem
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class MusicSystem {

    private ArrayList<Song> songs = new ArrayList<Song>();

    public MusicSystem() throws IOException, FileNotFoundException {
        File sf = new File("music.txt");
        Scanner sc = new Scanner(sf);
        sc.useDelimiter("\\*|\r\n|\n");
        while (sc.hasNext()) {
            int year = sc.nextInt();
            // System.out.println(year);
            int ranking = sc.nextInt();
            // System.out.println(ranking);
            String artist = sc.next();
            // System.out.println(artist);
            String title = sc.next();
            // System.out.println(title);
            int downloadCount = sc.nextInt();
            // System.out.println(downloadCount);
            Song song = new Song(year, ranking, artist, title, downloadCount);
            songs.add(song);
        } // while
        sc.close();
    } // MusicSystem()

    public void writeSorted() throws IOException {
        sortByRank();
        sortByYear();

        FileWriter fw = new FileWriter(new File("sortedMusic.txt"));

        for (Song s : songs)
            fw.write(s.getYear() + "*" + s.getRanking() + "*" + s.getArtist() + "*" + s.getTitle() + "*"
                    + s.getDownloadCount() + "\n");

        fw.close();
    }

    /**
     * @return the songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    } // getSongs()

    /**
     * @param songs the songs to set
     */
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    } // setSongs()

    /**
     * 
     */
    public void sortByYear() {
        int n = songs.size();
        for (int j = 0; j < songs.size(); j++) {
            for (int i = 1; i < (n - j); i++) {
                Song song1 = songs.get(i);
                Song song2 = songs.get(i - 1);
                if (song1.getYear() < song2.getYear()) {
                    songs.set(i, song2);
                    songs.set(i - 1, song1);
                }
            }
        }
    } // sortByYear()

    public void sortByRank() {
        int n = songs.size();
        for (int j = 0; j < songs.size(); j++) {
            for (int i = 1; i < (n - j); i++) {
                Song song1 = songs.get(i);
                Song song2 = songs.get(i - 1);
                if (song1.getRanking() < song2.getRanking()) {
                    songs.set(i, song2);
                    songs.set(i - 1, song1);
                }
            }
        }
    } // sortByRank()

    public void sortByTitle() {
        int n = songs.size();
        for (int j = 0; j < songs.size(); j++) {
            for (int i = 1; i < (n - j); i++) {
                Song song1 = songs.get(i);
                Song song2 = songs.get(i - 1);
                if (song1.getTitle().compareToIgnoreCase(song2.getTitle()) < 0) {
                    songs.set(i, song2);
                    songs.set(i - 1, song1);
                }
            }
        }
    } // sortByTitle()

    public Song findSongByTitle(String title) {
        // sort songs by title to enable a binary search
        sortByTitle();

        // Binary search
        int bottom = 0;
        int top = songs.size() - 1;
        Song found = null;

        while (bottom <= top && found == null) {

            int middle = (bottom + top) / 2;

            if (songs.get(middle).getTitle().equalsIgnoreCase(title))
                found = songs.get(middle);
            else if (songs.get(middle).getTitle().compareToIgnoreCase(title) < 0)
                bottom = middle + 1;
            else
                top = middle - 1;

        }

        return found;
    } // findSongByTitle()

    public Song findSongByYearAndRanking(int year, int ranking) {
        for (Song s : songs)
            if (s.getRanking() == ranking && s.getYear() == year)
                return s;

        return null;
    }
}
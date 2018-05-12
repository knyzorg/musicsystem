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

        // Presort it all
        sortByYearAndRank();

    } // MusicSystem()

    public void writeSorted() throws IOException {
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
    public void sortByYearAndRank() {
        int n = songs.size();
        boolean sorted = false;
        for (int j = 0; j < songs.size() && !sorted; j++) {
            sorted = true;
            for (int i = 1; i < (n - j); i++) {
                Song song1 = songs.get(i);
                Song song2 = songs.get(i - 1);
                if (song1.getYear() < song2.getYear()
                        || (song1.getYear() == song2.getYear() && song1.getRanking() < song2.getRanking())) {
                    songs.set(i, song2);
                    songs.set(i - 1, song1);
                    sorted = false;
                }
            }
        }
    } // sortByYear()

    public Song findSongByTitle(String title) {
        for (Song s : songs)
            if (s.getTitle().equals(title))
                return s;
        return null;

    } // findSongByTitle()

    public Song findSongByYearAndRanking(int year, int ranking) {
        for (Song s : songs)
            if (s.getRanking() == ranking && s.getYear() == year)
                return s;

        return null;
    }
}
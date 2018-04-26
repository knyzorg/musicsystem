/**
 * Song
 */
package vknyazev_B20_A03_MusicSystem;
public class Song {
    private int year;
    private int ranking;
    private String artist;
    private String title;
    private int downloadCount;

    Song(int year, int ranking, String artist, String title, int downloadCount) {
        this.year = year;
        this.ranking = ranking;
        this.artist = artist;
        this.title = title;
        this.downloadCount = downloadCount;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the ranking
     */
    public int getRanking() {
        return ranking;
    }

    /**
     * @param ranking the ranking to set
     */
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param downloadCount the downloadCount to set
     */
    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    /**
     * @return the downloadCount
     */
    public int getDownloadCount() {
        return downloadCount;
    }

}
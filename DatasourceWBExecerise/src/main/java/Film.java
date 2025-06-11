public class Film {
    int filmId;
    String title;
    String description;
    int releaseYear;
    int length;


    public Film(int filmId, String title, String description, int releaseYear, int length) {
        if (title == null || title.isBlank() || title.isEmpty()) {
            throw new IllegalArgumentException("Invalid input. Please enter correct title name.");
        }
       this.filmId = filmId;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }






}

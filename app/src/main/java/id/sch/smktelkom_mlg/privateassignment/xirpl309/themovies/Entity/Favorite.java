package id.sch.smktelkom_mlg.privateassignment.xirpl309.themovies.Entity;

import com.orm.SugarRecord;

/**
 * Created by elvirayu on 15/05/2017.
 */

public class Favorite extends SugarRecord {
    int movieId;
    String note;

    public Favorite() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

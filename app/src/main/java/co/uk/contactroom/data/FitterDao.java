package co.uk.contactroom.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import co.uk.contactroom.model.Fitter;

@Dao
public interface FitterDao {

    // CRUD

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Fitter contact);

    @Query("DELETE FROM fitter_table")
    void deleteAll();

    @Query("SELECT * FROM fitter_table ORDER BY name ASC")
    LiveData<List<Fitter>> getAllContacts();

    @Query("SELECT * FROM fitter_table WHERE name==:name")
    LiveData<Fitter> getContactByName(String name);

}

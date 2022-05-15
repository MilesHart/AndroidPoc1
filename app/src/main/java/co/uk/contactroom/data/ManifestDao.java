package co.uk.contactroom.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import co.uk.contactroom.model.Manifest;

@Dao
public interface ManifestDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Manifest manifest);



    @Query("SELECT * FROM manifest_table")
    LiveData<List<Manifest>> getAllManifests();
}

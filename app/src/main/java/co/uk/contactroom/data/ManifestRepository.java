package co.uk.contactroom.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;


import co.uk.contactroom.model.Manifest;
import co.uk.contactroom.util.EsignRoomDatabase;

public class ManifestRepository {
    private ManifestDao manifestDao;
    private LiveData<List<Manifest>> allManifests;

    public ManifestRepository(Application application) {
        EsignRoomDatabase db = EsignRoomDatabase.getDatabase(application);

        manifestDao = db.manifestDao();

        allManifests = manifestDao.getAllManifests();
    }


    public void insert(Manifest manifest){
        EsignRoomDatabase.databaseWriteExecutor.execute(() -> {
            manifestDao.insert(manifest);
        });
    }

}

package co.uk.contactroom.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import co.uk.contactroom.model.Fitter;
import co.uk.contactroom.util.EsignRoomDatabase;

public class FitterRepository {
    private FitterDao fitterDao;
    private LiveData<List<Fitter>> allFitters;


    public FitterRepository(Application application) {
        EsignRoomDatabase db = EsignRoomDatabase.getDatabase(application);
        fitterDao = db.fitterDao();

        allFitters = fitterDao.getAllContacts();
    }

    public LiveData<List<Fitter>> getAllData(){
        return allFitters;
    }


    public LiveData<Fitter> getContact(String name){
        return fitterDao.getContactByName(name);
    }

    public void insert(Fitter contact){
        EsignRoomDatabase.databaseWriteExecutor.execute(() -> {
            fitterDao.insert(contact);
        });
    }

}

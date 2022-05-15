package co.uk.contactroom.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import co.uk.contactroom.data.FitterRepository;

public class FitterViewModel extends AndroidViewModel {

    public static FitterRepository repository;
    public final LiveData<List<Fitter>> allContacts;
    public final Fitter contact;


    public FitterViewModel(@NonNull Application application) {
        super(application);
        repository = new FitterRepository(application) ;
        allContacts = repository.getAllData();
        contact = null;
    }

    public LiveData<List<Fitter>> getAllContacts() {
        return allContacts;
    }

    public LiveData<Fitter> getContact(String name){
        return repository.getContact(name);
    }

    public static void insert(Fitter contact){

        repository.insert(contact);
    }


}

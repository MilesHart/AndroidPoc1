package co.uk.contactroom.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import co.uk.contactroom.model.Contact;
import co.uk.contactroom.util.ContactRoomDatabase;

public class ContactRepository {
    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;


    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        contactDao = db.contactDao();

        allContacts = contactDao.getAllContacts();

    }

    public LiveData<List<Contact>> getAllData(){return allContacts;}
    public void insert(Contact contact){
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            contactDao.insert(contact);
        });
    }
}
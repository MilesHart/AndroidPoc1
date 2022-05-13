package co.uk.contactroom.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import co.uk.contactroom.data.ContactDao;
import co.uk.contactroom.model.Contact;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();

    public static final int NUMBER_OF_THREADS = 4;

    private static volatile ContactRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ContactRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (ContactRoomDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contact_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    databaseWriteExecutor.execute(() ->{
                        ContactDao contactDao = INSTANCE.contactDao();
                        contactDao.deleteAll();

                        Contact contact = new Contact("Milo", "Dev");
                        contactDao.insert(contact);

                        contact = new Contact("Bond", "Spy");
                        contactDao.insert(contact);

                    });
                }
            };
}

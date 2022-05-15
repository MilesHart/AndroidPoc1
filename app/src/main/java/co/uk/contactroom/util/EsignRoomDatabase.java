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
import co.uk.contactroom.data.FitterDao;
import co.uk.contactroom.data.ManifestDao;
import co.uk.contactroom.model.Fitter;
import co.uk.contactroom.model.Manifest;

@Database(entities = {Fitter.class, Manifest.class}, version = 1, exportSchema = false)
public abstract class EsignRoomDatabase extends RoomDatabase {

    public abstract FitterDao fitterDao();
    public abstract ManifestDao manifestDao();

    public static final int NUMBER_OF_THREADS = 4;

    private static volatile EsignRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static EsignRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (EsignRoomDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EsignRoomDatabase.class, "esign_database")
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
                        FitterDao contactDao = INSTANCE.fitterDao();
                        contactDao.deleteAll();

                        Fitter contact = new Fitter("Milo", "Dev", "1234");
                        contactDao.insert(contact);

                        contact = new Fitter("Bond", "Spy", "1234");
                        contactDao.insert(contact);


                        contact = new Fitter("Billy", "Plumber", "1234");
                        contactDao.insert(contact);




                    });
                }
            };
}

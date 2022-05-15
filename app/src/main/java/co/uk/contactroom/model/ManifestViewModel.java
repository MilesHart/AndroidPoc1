package co.uk.contactroom.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import co.uk.contactroom.data.FitterRepository;
import co.uk.contactroom.data.ManifestRepository;

public class ManifestViewModel extends AndroidViewModel {
    public static ManifestRepository repository;

    public ManifestViewModel(@NonNull Application application) {
        super(application);
        repository = new ManifestRepository(application) ;
    }
}

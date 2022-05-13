package co.uk.contactroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import co.uk.contactroom.model.Contact;
import co.uk.contactroom.model.ContactViewModel;

public class MainActivity extends AppCompatActivity {

    private ContactViewModel contactViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(ContactViewModel.class);

        contactViewModel.getAllContacts().observe(this, contacts -> {
            for(Contact contact: contacts){
                Log.d("TAG", "onCreate: " + contact.getName());
            }

        });
    }
}
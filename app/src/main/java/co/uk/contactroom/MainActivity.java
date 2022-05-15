package co.uk.contactroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import co.uk.contactroom.model.Fitter;
import co.uk.contactroom.model.FitterViewModel;

public class MainActivity extends AppCompatActivity {

    private FitterViewModel fitterViewModel;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tv = findViewById(R.id.tv1);
        fitterViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(FitterViewModel.class);


        fitterViewModel.getAllContacts().observe(this, contacts -> {
            StringBuilder sb = new StringBuilder();
            for(Fitter contact: contacts){
                Log.d("TAG", "onCreate: " + contact.getName());
                sb.append(contact.getName() + " ");
            }
            tv.setText(sb.toString());

        });


    }
}
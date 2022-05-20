package co.uk.contactroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.uk.contactroom.model.ApiFitter;
import co.uk.contactroom.model.Fitter;
import co.uk.contactroom.model.FitterViewModel;
import co.uk.contactroom.model.LoginResp;
import co.uk.contactroom.remote.ApiUtils;
import co.uk.contactroom.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class   LoginActivity extends AppCompatActivity {

    private FitterViewModel fitterViewModel;

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;

    UserService userService;













    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fitterViewModel = new ViewModelProvider.AndroidViewModelFactory(LoginActivity.this
                .getApplication())
                .create(FitterViewModel.class);


        userService = ApiUtils.getUserService();

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);


                                fitterViewModel.getAllContacts().observe(this, fitters -> {
                            StringBuilder sb = new StringBuilder();
                            for(Fitter fiter: fitters){
                                Log.d("TAG", "onCreate: " + fiter.getName());
                                sb.append(fiter.getName() + " ");
                            }
                                    Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();


                        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                //validate form
                if(validateLogin(username, password)){
                    //do login
                    doLogin(username, password);
                }
            }
        });


    }

    private boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String username,final String password) {
        ApiFitter apifitter = new ApiFitter();
        apifitter.setName(username);
        apifitter.setPin(password);



        fitterViewModel.getContact("Milo").observe(this, fitters -> {
            Toast.makeText(this, fitters.getPin(), Toast.LENGTH_SHORT).show();
        });
//        fitterViewModel.getAllContacts().observe(this, fitters -> {
//                            StringBuilder sb = new StringBuilder();
//                            for(Fitter fiter: fitters){
//                                Log.d("TAG", "onCreate: " + fiter.getName());
//                                sb.append(fiter.getName() + " ");
//                            }
//            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
//                        });

        // Send a REST request
        Call call = userService.login(apifitter);


        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("RESP", response.body().toString());
                if(response.isSuccessful()){

                    LoginResp resObj = (LoginResp) response.body();
                    if(resObj.getSuccess().equals("success")){






//                        fitterViewModel.getAllContacts().observe(this, fitters -> {
//                            StringBuilder sb = new StringBuilder();
//                            for(Fitter fiter: fitters){
//                                Log.d("TAG", "onCreate: " + fiter.getName());
//                                sb.append(fiter.getName() + " ");
//                            }
//                            tv.setText(sb.toString());
//
//                        });



//                        if (dbHandler.IsFitterExists(fitter.getName())){
//                            dbHandler.updateFitter(fitter.getName(), fitter.getPin(), resObj.getAccess_token(), resObj.getResource_id());
//                            Toast.makeText(LoginActivity.this, "Online login successful, updating local cache", Toast.LENGTH_SHORT).show();
//                        }else {
//
//                            dbHandler.addNewFitter(fitter.getName(), fitter.getPin(), resObj.getAccess_token(), resObj.getResource_id());
//                            Toast.makeText(LoginActivity.this, "Online login successful, adding to local cache", Toast.LENGTH_SHORT).show();
//                        }

                        //login start main activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Error! Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (t.getMessage().contains("No address associated with hostname")){
                    // OFFLINE MODE
                    Toast.makeText(LoginActivity.this, "Offline mode, using cached data.", Toast.LENGTH_LONG).show();
                    //AouFitter fiter = dbHandler.localLogin("jason collins","2491");


                }
                else if (t.getMessage().equals("java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 2 path $")) {
                    //errormessage.setText("Incorrect username/password");
                    Toast.makeText(LoginActivity.this, "Incorrect username/password", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
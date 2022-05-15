package co.uk.contactroom.remote;

import co.uk.contactroom.model.ApiFitter;
import co.uk.contactroom.model.Fitter;
import co.uk.contactroom.model.LoginResp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("fitter_login")
    Call<LoginResp> login(@Body ApiFitter apifitter);
}

package co.uk.contactroom.remote;

public class ApiUtils {
    public static final String BASE_URL = "http://esign.dbd.co.uk/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}

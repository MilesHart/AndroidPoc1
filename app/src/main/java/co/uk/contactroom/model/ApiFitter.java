package co.uk.contactroom.model;

import com.google.gson.annotations.SerializedName;

public class ApiFitter {

    public ApiFitter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @SerializedName("email")
    private String name;


    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    @SerializedName("password")
    private String pin;

    private String secret;


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

package co.uk.contactroom.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResp {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("resource_id")
    @Expose
    private String resourceId;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getAccess_token() {
        return accessToken;
    }

    public void setAccess_token(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getResource_id() {
        return resourceId;
    }

    public void setResource_id(String resourceId) {
        this.resourceId = resourceId;
    }
}

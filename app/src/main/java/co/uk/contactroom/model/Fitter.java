package co.uk.contactroom.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.sql.Date;

@Entity(tableName = "Fitter_table")
public class Fitter {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="name")
    @NonNull
    private String name;


    @ColumnInfo(name="key")
    private String key;

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ColumnInfo(name="lastlogin")
    private String lastlogin;


    @ColumnInfo(name="pin")
    @NonNull
    private String pin;

    @ColumnInfo(name="email")
    private String email;



    public Fitter(@NonNull String name, String email, String pin) {
        this.name = name;
        this.email = email;
        this.pin = pin;
        this.key = "supersecretkey";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @NonNull
    public String getPin() {return pin;}
    public void setPin(@NonNull String pin) {this.pin = pin;}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

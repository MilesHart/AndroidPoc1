package co.uk.contactroom.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Manifest_table")
public class Manifest {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManifest_number() {
        return manifest_number;
    }

    public void setManifest_number(int manifest_number) {
        this.manifest_number = manifest_number;
    }

    public String getManifest_date() {
        return manifest_date;
    }

    public void setManifest_date(String manifest_date) {
        this.manifest_date = manifest_date;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="manifest_number")
    private int manifest_number;

    @ColumnInfo(name="manifest_date")
    private String manifest_date;

    @ColumnInfo(name="resource")
    private String resource;

    @ColumnInfo(name="vehicle")
    private String vehicle;
}

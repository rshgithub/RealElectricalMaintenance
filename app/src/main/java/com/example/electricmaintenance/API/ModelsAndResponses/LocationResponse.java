
package com.example.electricmaintenance.API.ModelsAndResponses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LocationResponse {

    @SerializedName("location")
    @Expose
    private List<String> location ;

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

}

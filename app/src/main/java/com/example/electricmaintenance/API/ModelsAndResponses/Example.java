
package com.example.electricmaintenance.API.ModelsAndResponses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Example {

    @SerializedName("State")
    @Expose
    private List<State> state = null;

    public List<State> getState() {
        return state;
    }

    public void setState(List<State> state) {
        this.state = state;
    }

}

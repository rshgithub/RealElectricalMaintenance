
package com.example.electricmaintenance.API.ModelsAndResponses;

import java.util.List;
import javax.annotation.Generated;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Area {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("Transferor")
    @Expose
    private List<Transferor> transferor = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transferor> getTransferor() {
        return transferor;
    }

    public void setTransferor(List<Transferor> transferor) {
        this.transferor = transferor;
    }

}

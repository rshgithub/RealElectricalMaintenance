package com.example.electricmaintenance.API.ModelsAndResponses;
import java.io.Serializable;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Links implements Serializable
{

    @SerializedName("first")
    @Expose
    private String first;
    @SerializedName("last")
    @Expose
    private String last;
    @SerializedName("prev")
    @Expose
    private Object prev;
    @SerializedName("next")
    @Expose
    private String next;
    private final static long serialVersionUID = 2253736856319719178L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Links() {
    }

    /**
     *
     * @param next
     * @param last
     * @param prev
     * @param first
     */
    public Links(String first, String last, Object prev, String next) {
        super();
        this.first = first;
        this.last = last;
        this.prev = prev;
        this.next = next;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Object getPrev() {
        return prev;
    }

    public void setPrev(Object prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
package com.example.camerxtests.base.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PublicationResponse {
    @SerializedName("response")
    @Expose
    private List<Publication> response = null;

    public List<Publication> getResponse() {
        return response;
    }

    public void setResponse(List<Publication> response) {
        this.response = response;
    }
}

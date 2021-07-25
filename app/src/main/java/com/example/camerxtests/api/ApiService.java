package com.example.camerxtests.api;



import com.example.camerxtests.base.pojo.PublicationResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("testTask.json")
    Observable<PublicationResponse> getPublications();
}

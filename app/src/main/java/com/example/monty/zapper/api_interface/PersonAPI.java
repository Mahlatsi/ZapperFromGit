package com.example.monty.zapper.api_interface;

import com.example.monty.zapper.medels.PersonDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Monty on 5/7/2017.
 */

public interface PersonAPI {

    @GET("person")
    Call<List<PersonDetails>> getItems();
    @GET("person/{id}")
    Call<PersonDetails> getDetails(@Path("id")int id);
}

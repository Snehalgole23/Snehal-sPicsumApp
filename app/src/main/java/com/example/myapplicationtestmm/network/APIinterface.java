package com.example.myapplicationtestmm.network;

import com.example.myapplicationtestmm.model.Author;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {
    @GET("list")
    Call<List<Author>> getAuthor();

}

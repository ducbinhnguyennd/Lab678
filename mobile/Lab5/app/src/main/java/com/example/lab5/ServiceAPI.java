package com.example.lab5;

import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceAPI {
    @GET("users")
    Call<List<User>> getAllUser();
//    @GET("users")
//    Call<JsonElement> getAllUser();
    @POST("users/add-user")
    Call<User> createUser(@Body User user);
    @PUT("users/edit-user/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User user);
    @DELETE("users/delete-user/{id}")
    Call<Void> deleteUser(@Path("id") String id);

}

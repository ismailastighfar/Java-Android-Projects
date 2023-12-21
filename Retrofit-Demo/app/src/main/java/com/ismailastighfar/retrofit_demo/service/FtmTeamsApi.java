package com.ismailastighfar.retrofit_demo.service;

import com.ismailastighfar.retrofit_demo.model.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FtmTeamsApi {
    @GET("teams")
    Call<List<Team>> getTeams();
    @GET("teams/{id}")
    Call<Team> getTeamById(@Path("id") int id);
    @POST("teams")
    Call<Team> createTeam(@Body Team team);
    @PUT("teams/{id}")
    Call<Team> updateTeam(@Path("id") int id, @Body Team team);
    @DELETE("teams/{id}")
    Call<Void> deleteTeam(@Path("id") int id);
}

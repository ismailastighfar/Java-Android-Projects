package com.ismailastighfar.retrofit_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ismailastighfar.retrofit_demo.model.Team;
import com.ismailastighfar.retrofit_demo.service.FtmTeamsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        TextView responseResult = findViewById(R.id.result);
        EditText editTextID = findViewById(R.id.editTextId);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextCountry = findViewById(R.id.editTextCountry);
        Button btnCreate = findViewById(R.id.btnCreate);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnList = findViewById(R.id.btnList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = editTextName.getText().toString();
                String countrytxt = editTextCountry.getText().toString();
                Team team = new Team(countrytxt,nametxt);
                FtmTeamsApi ftmTeamsApi = retrofit.create(FtmTeamsApi.class);
                Call<Team> call = ftmTeamsApi.createTeam(team);
                call.enqueue(new Callback<Team>() {
                    @Override
                    public void onResponse(Call<Team> call, Response<Team> response) {
                        if (!response.isSuccessful()) {
                            responseResult.setText("Code: " + response.code());
                            return;
                        }
                        Team teamResponse = response.body();

                        String content = "";
                        content += "Code: " + response.code() + "\n";
                        content += "ID: " + teamResponse.id + "\n";
                        content += "Name: " + teamResponse.name + "\n";
                        content += "Country: " + teamResponse.country + "\n";
                        responseResult.setText(content);
                    }

                    @Override
                    public void onFailure(Call<Team> call, Throwable t) {
                        responseResult.setText(t.getMessage());
                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idStr = editTextID.getText().toString();
                int id = Integer.parseInt(idStr);
                String name = editTextName.getText().toString();
                String country = editTextCountry.getText().toString();
                Team team = new Team(country,name);
                FtmTeamsApi ftmTeamsApi = retrofit.create(FtmTeamsApi.class);
                Call<Team> call = ftmTeamsApi.updateTeam(id,team);
                call.enqueue(new Callback<Team>() {
                    @Override
                    public void onResponse(Call<Team> call, Response<Team> response) {
                        if (!response.isSuccessful()) {
                            responseResult.setText("Code: " + response.code());
                            return;
                        }
                        Team teamResponse = response.body();

                        String content = "";
                        content += "Code: " + response.code() + "\n";
                        content += "ID: " + teamResponse.id + "\n";
                        content += "Name: " + teamResponse.name + "\n";
                        content += "Country: " + teamResponse.country + "\n";
                        responseResult.setText(content);
                    }

                    @Override
                    public void onFailure(Call<Team> call, Throwable t) {
                        responseResult.setText(t.getMessage());
                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idStr = editTextID.getText().toString();
                int id = Integer.parseInt(idStr);
                FtmTeamsApi ftmTeamsApi = retrofit.create(FtmTeamsApi.class);
                Call<Void> call = ftmTeamsApi.deleteTeam(id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        responseResult.setText("Code: " + response.code());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        responseResult.setText(t.getMessage());
                    }
                });
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idStr = editTextID.getText().toString();

                int id = Integer.parseInt(idStr);
                FtmTeamsApi ftmTeamsApi = retrofit.create(FtmTeamsApi.class);
                Call<Team> call = ftmTeamsApi.getTeamById(id);
                call.enqueue(new Callback<Team>() {
                    @Override
                    public void onResponse(Call<Team> call, Response<Team> response) {
                        if (!response.isSuccessful()) {
                            responseResult.setText("Code: " + response.code());
                            return;
                        }
                        Team teamResponse = response.body();

                        String content = "";
                        content += "ID: " + teamResponse.id + "\n";
                        content += "Name: " + teamResponse.name + "\n";
                        content += "Country: " + teamResponse.country + "\n";
                        responseResult.setText(content);
                    }

                    @Override
                    public void onFailure(Call<Team> call, Throwable t) {
                        responseResult.setText(t.getMessage());
                    }
                });
            }
        });

    }

     /*  public void createTeam(){
            String nametxt = editTextName.getText().toString();
            String countrytxt = editTextCountry.getText().toString();
            Team team = new Team(countrytxt,nametxt);

           Call<Team> call = ftmTeamsApi.createTeam(team);
           call.enqueue(new Callback<Team>() {
               @Override
               public void onResponse(Call<Team> call, Response<Team> response) {
                   if (!response.isSuccessful()) {
                       responseResult.setText("Code: " + response.code());
                       return;
                   }
                   Team teamResponse = response.body();

                   String content = "";
                   content += "Code: " + response.code() + "\n";
                   content += "ID: " + teamResponse.id + "\n";
                   content += "Name: " + teamResponse.name + "\n";
                   content += "Country: " + teamResponse.country + "\n";
                   responseResult.setText(content);
               }

               @Override
               public void onFailure(Call<Team> call, Throwable t) {
                 responseResult.setText(t.getMessage());
               }
           });
        }

    public void updateTeam(){
        String idStr = editTextID.getText().toString();
        int id = Integer.parseInt(idStr);
        String name = editTextName.getText().toString();
        String country = editTextCountry.getText().toString();
        Team team = new Team(name,country);

        Call<Team> call = ftmTeamsApi.updateTeam(id,team);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (!response.isSuccessful()) {
                    responseResult.setText("Code: " + response.code());
                    return;
                }
                Team teamResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + teamResponse.id + "\n";
                content += "Name: " + teamResponse.name + "\n";
                content += "Country: " + teamResponse.country + "\n";
                responseResult.setText(content);
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                responseResult.setText(t.getMessage());
            }
        });
    }

    public void deleteTeam(){
        String idStr = editTextID.getText().toString();
        int id = Integer.parseInt(idStr);

        Call<Void> call = ftmTeamsApi.deleteTeam(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                    responseResult.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                   responseResult.setText(t.getMessage());
            }
        });
    }*/
}
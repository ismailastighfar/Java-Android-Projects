package com.me.gitapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.me.gitapp.model.GitRepo;
import com.me.gitapp.service.GitRepoServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryActivity extends AppCompatActivity {
    List<String> data = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_layout);
        Intent intent = getIntent();
        String username = intent.getStringExtra(MainActivity.USER_LOGIN);
        setTitle("Repositories");
        TextView login = findViewById(R.id.login);
        ListView repositories = findViewById(R.id.listRepositories);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        repositories.setAdapter(arrayAdapter);
        login.setText(username);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitRepoServiceApi gitRepoServiceApi = retrofit.create(GitRepoServiceApi.class);
        Call<List<GitRepo>> reposCall = gitRepoServiceApi.userRepositories(username);
        reposCall.enqueue(new Callback<List<GitRepo>>() {
            @Override
            public void onResponse(Call<List<GitRepo>> call, Response<List<GitRepo>> response) {
               if (!response.isSuccessful()){
                   Log.e("error",String.valueOf(response.code()) );
               }
               List<GitRepo> gitRepos = response.body();
               for (GitRepo gitRepo:gitRepos){
                   String content="";
                   content+=gitRepo.id+"\n";
                   content+=gitRepo.name+"\n";
                   content+=gitRepo.language+"\n";
                   content+=gitRepo.size+"\n";
                   data.add(content);
               }
               arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitRepo>> call, Throwable t) {

            }
        });
    }
}

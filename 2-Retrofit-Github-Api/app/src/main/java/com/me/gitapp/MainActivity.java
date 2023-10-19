package com.me.gitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.me.gitapp.model.GitUser;
import com.me.gitapp.model.GitUsersResponse;
import com.me.gitapp.model.UsersListViewModel;
import com.me.gitapp.service.GitRepoServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<GitUser> data = new ArrayList<>();
    public static final String USER_LOGIN = "user.login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        EditText inputSearch = findViewById(R.id.inputSearch);
        Button btnSearch = findViewById(R.id.btnSearch);
        ListView listViewUsers = findViewById(R.id.listUsers);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        UsersListViewModel viewModel = new UsersListViewModel(this,R.layout.users_list_view,data);
        listViewUsers.setAdapter(viewModel);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String query = inputSearch.getText().toString();
                GitRepoServiceApi gitRepoServiceApi = retrofit.create(GitRepoServiceApi.class);
                Call<GitUsersResponse> callGitUsers = gitRepoServiceApi.searchUsers(query);
                callGitUsers.enqueue(new Callback<GitUsersResponse>() {
                    @Override
                    public void onResponse(Call<GitUsersResponse> call, Response<GitUsersResponse> response) {
                       if (!response.isSuccessful()){
                           Log.i("info", String.valueOf(response.code()));
                       }
                       GitUsersResponse usersResponse = response.body();
                       data.clear();
                       for (GitUser user:usersResponse.users){
                           data.add(user);
                       }
                        viewModel.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<GitUsersResponse> call, Throwable t) {
                        Log.e("error" ,"onFailure: ");
                    }
                });
            }
        });

        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String login = data.get(i).login;
                Intent intent= new Intent(getApplicationContext(),RepositoryActivity.class);
                intent.putExtra(USER_LOGIN,login);
                startActivity(intent);
            }
        });
    }
}
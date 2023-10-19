package com.me.gitapp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.me.gitapp.R;

import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.http.Url;

public class UsersListViewModel extends ArrayAdapter<GitUser> {
    private int resource;
    public UsersListViewModel(@NonNull Context context, int resource ,List<GitUser> users) {
        super(context, resource,users);
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItems = convertView;
        if (listItems == null){
            listItems = LayoutInflater.from(getContext()).inflate(resource,parent,false);
        }
        CircleImageView imageView = listItems.findViewById(R.id.imageViewUser);
        TextView textViewLogin = listItems.findViewById(R.id.textViewLogin);
        TextView textViewScore = listItems.findViewById(R.id.textViewScore);
        try {
            URL url = new URL(getItem(position).avatarUrl);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
            imageView.setImageBitmap(bitmap);
        }catch(Exception e){
           e.printStackTrace();
        }
        textViewLogin.setText(getItem(position).login);
        textViewScore.setText(String.valueOf(getItem(position).score));
        return listItems;
    }
}

package com.ismailastighfar.verification_age;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName , editTextDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextDOB = findViewById(R.id.editTextDOB);
    }

    public void verifyAge(View view){
        String name = editTextName.getText().toString();
        String dobString = editTextDOB.getText().toString();

        if (!name.isEmpty() && !dobString.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dob = dateFormat.parse(dobString);
                Date currentDate = new Date();

                long ageInMillis = currentDate.getTime() - dob.getTime();
                long ageInYears = ageInMillis / (365L * 24 * 60 * 60 * 1000);

                if (ageInYears >= 18) {
                    //showMessage("Age verification successful. Welcome, " + name + "!",6000);
                    showSnackBar(MainActivity.this, "Age verification successful. Welcome, " + name + "!", Snackbar.LENGTH_LONG);
                } else {
                    //showMessage("Sorry, " + name + ", you must be 18 or older.",6000);
                    showSnackBar(MainActivity.this, "Sorry, " + name + ", you must be 18 or older.", Snackbar.LENGTH_LONG);
                }

            } catch (ParseException e) {
                //showMessage("Invalid date format. Please use YYYY-MM-DD.",6000);
                showSnackBar(MainActivity.this, "Invalid date format. Please use YYYY-MM-DD.", Snackbar.LENGTH_LONG);
            }
        } else {
            //showMessage("Please enter your name and date of birth.",6000);
            showSnackBar(MainActivity.this, "Please enter your name and date of birth", Snackbar.LENGTH_LONG);
        }
    }
    public void quitApp(View view) {
        finish();
    }

    private void showMessage(String message,int durationMillis) {
        final Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        // Set the position of the Toast above the labels
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);

        // Show the Toast
        toast.show();

        // Use a Handler to delay the Toast dismissal
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, durationMillis);
    }

    public void showSnackBar(Activity activity, String message,int duration){
        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(rootView, message, duration);
        // Set the gravity to TOP
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackbar.getView().getLayoutParams();
        params.gravity = Gravity.TOP;
        params.topMargin = getResources().getDimensionPixelOffset(R.dimen.snackbar_vertical_offset);
        // Change the background color
        snackbar.getView().setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.bgBtn)));

        // Apply the layout parameters
        snackbar.getView().setLayoutParams(params);
        snackbar.show();
    }

}
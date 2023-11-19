 package com.ismailastighfar.convertisseur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

 public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private Spinner spinnerCurrency;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        spinnerCurrency = findViewById(R.id.spinnerCurrency);
        textViewResult = findViewById(R.id.textViewResult);

        // Populate the spinner with currency options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.currency_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(adapter);

        Button buttonConvert = findViewById(R.id.buttonConvert);
        Button buttonClose = findViewById(R.id.buttonClose);

        // Set click listeners for the buttons
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertCurrency();
            }
        });

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the application
            }
        });
    }

     private void convertCurrency() {
         // Get the amount entered by the user
         String amountStr = editTextAmount.getText().toString().trim();

         if (amountStr.isEmpty()) {
             // Show an error message or toast indicating that the amount is empty
             showSnackBar(MainActivity.this, "Please enter an amount", Snackbar.LENGTH_LONG);
             return;
         }

         double amount = Double.parseDouble(amountStr);

         // Get the selected currency
         String selectedCurrency = spinnerCurrency.getSelectedItem().toString();

         // Perform the conversion based on the selected currency
         double convertedAmount = 0;
         switch (selectedCurrency) {
             case "Euro":
                 convertedAmount = amount * 11.02;
                 break;
             case "Dollar Americain":
                 convertedAmount = amount * 9.86;
                 break;
             case "Dollar Canadien":
                 convertedAmount = amount * 7.03;
                 break;
         }

         // Display the result
         textViewResult.setText(String.format("%.2f", convertedAmount));
     }

     public void showSnackBar(Activity activity, String message, int duration){
         View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
         Snackbar snackbar = Snackbar.make(rootView, message, duration);
         // Set the gravity to TOP
         FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackbar.getView().getLayoutParams();
         params.gravity = Gravity.TOP;
         params.topMargin = getResources().getDimensionPixelOffset(R.dimen.snackbar_vertical_offset);
         // Change the background color
         snackbar.getView().setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.btn)));

         // Apply the layout parameters
         snackbar.getView().setLayoutParams(params);
         snackbar.show();
     }
}
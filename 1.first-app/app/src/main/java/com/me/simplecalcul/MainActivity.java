package com.me.simplecalcul;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textResults = findViewById(R.id.textViewResults);
        ListView listResults = findViewById(R.id.listViewResults);
        Button btnCompute = findViewById(R.id.btnCompute);
        EditText editAmount = findViewById(R.id.txtAmount);

        List<String> data = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        listResults.setAdapter(arrayAdapter);
        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double amount = Double.parseDouble(editAmount.getText().toString());
                double result = amount*60;
                textResults.setText(String.valueOf(result));
                data.add(amount+"=>"+result);
                arrayAdapter.notifyDataSetChanged();
                editAmount.setText("");
            }
        });
    }
}
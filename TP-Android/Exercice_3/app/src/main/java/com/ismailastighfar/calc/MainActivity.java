package com.ismailastighfar.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText num1EditText, num2EditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1EditText = findViewById(R.id.num1EditText);
        num2EditText = findViewById(R.id.num2EditText);
        resultTextView = findViewById(R.id.resultTextView);

        // Set up buttons
        Button addButton = findViewById(R.id.addButton);
        Button subtractButton = findViewById(R.id.subtractButton);
        Button multiplyButton = findViewById(R.id.multiplyButton);
        Button divideButton = findViewById(R.id.divideButton);
        Button quitButton = findViewById(R.id.btnQuit);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('+');
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('-');
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('*');
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('/');
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the app
            }
        });
    }

    private void performOperation(char operator) {
        try {
            int num1 = Integer.parseInt(num1EditText.getText().toString());
            int num2 = Integer.parseInt(num2EditText.getText().toString());

            int result = 0;
            double res = 0;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    resultTextView.setText("the addition is : " + result);
                    break;
                case '-':
                    result = num1 - num2;
                    resultTextView.setText("the subtraction is : " + result);
                    break;
                case '*':
                    result = num1 * num2;
                    resultTextView.setText("the multiplication is : " + result);
                    break;
                case '/':
                    if (num2 != 0) {
                        res = (double) num1 / num2;
                        String formattedResult = String.format("%.2f", res);
                        resultTextView.setText("the division is : " + formattedResult);
                    } else {
                        resultTextView.setText("Cannot divide by zero!");
                        return;
                    }
                    break;
            }

        } catch (NumberFormatException e) {
            resultTextView.setText("Invalid input. Please enter valid numbers.");
        }
    }
}

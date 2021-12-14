package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.Format;

public class MainActivity extends AppCompatActivity {

    private EditText textMain;
    private TextView textFibNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textMain = (EditText) findViewById(R.id.text_main);
        textFibNumber = findViewById(R.id.text_fib_number);
        textMain.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                int i = Integer.parseInt(s.toString());
                int fibNumber = calculateFibonacciNumber(i);
                if (fibNumber == -1) {
                    textFibNumber.setText(getResources().getString(R.string.negative_error_string));
                } else if (fibNumber == -2) {
                    textFibNumber.setText(getResources().getString(R.string.overflow_error_string));
                } else {
                    String outputTemp = getResources().getString((R.string.output_string));
                    String output = String.format(outputTemp, Integer.parseInt(s.toString()), fibNumber);
                    textFibNumber.setText(output);
                }
            } catch (Exception e) {
                String error = getResources().getString((R.string.int_error_string));
                textFibNumber.setText(error);
            }
        }
    };

    public int calculateFibonacciNumber(int i) {
        if (i > 46) {
            return -2;
        }
        if (i < 0) {
            return -1;
        }
        if (i == 0) {
            return 0;
        } else if (i == 1) {
            return 1;
        }
        int param1 = 0;
        int param2 = 1;
        int fibNumber = 0;
        for (int j = 1; j < i; j++) {
            fibNumber = param1 + param2;
            param1 = param2;
            param2 = fibNumber;
        }
        return fibNumber;
    }
}
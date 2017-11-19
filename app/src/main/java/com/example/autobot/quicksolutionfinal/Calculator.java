package com.example.autobot.quicksolutionfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    private EditText e1, e2;
    private TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        t1 = (TextView) findViewById(R.id.textView3);
    }
    public void functioncalculator(View v) {
        if (v.getId() == R.id.button) {
            Double number1= Double.parseDouble(e1.getText().toString());
            Double number2= Double.parseDouble(e2.getText().toString());
            double result = number1 + number2;
            t1.setText(Double.toString(result));
        }
        if (v.getId() == R.id.button2) {
            Double number1= Double.parseDouble(e1.getText().toString());
            Double number2= Double.parseDouble(e2.getText().toString());
            double result = number1 - number2;
            t1.setText(Double.toString(result));
        }
        if (v.getId() == R.id.button3) {
            Double number1= Double.parseDouble(e1.getText().toString());
            Double number2= Double.parseDouble(e2.getText().toString());
            double result = number1 * number2;
            t1.setText(Double.toString(result));
        }
        if (v.getId() == R.id.button4) {
            Double number1= Double.parseDouble(e1.getText().toString());
            Double number2= Double.parseDouble(e2.getText().toString());
            double result = number1 / number2;
            t1.setText(Double.toString(result));
        }
        if (v.getId() == R.id.button5) {
            Double number1= Double.parseDouble(e1.getText().toString());
            double result = number1 * number1;
            t1.setText(Double.toString(result));
        }
        if (v.getId() == R.id.button6) {
            Double number1= Double.parseDouble(e1.getText().toString());
            double result = number1 * number1 * number1;
            t1.setText(Double.toString(result));
        }
        if (v.getId() == R.id.button7) {
            Double number1= Double.parseDouble(e1.getText().toString());
            double result = Math.sqrt(number1);
            t1.setText(Double.toString(result));
        }
    }
}

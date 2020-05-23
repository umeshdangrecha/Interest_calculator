package com.umesh.patidar.interestcalculator;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText amount, rate;
    private TextView date1, date2, date, days, amo, interest, totalAmount;
    private Button submit;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final int month = Calendar.getInstance().get(Calendar.MONTH);
        final int year = Calendar.getInstance().get(Calendar.YEAR);
        String s = String.format("%02d", day) + "/" + String.format("%02d", 1 + month) + "/" + year;
        date1.setText(s);
        date2.setText(s);


        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String s = String.format("%02d", dayOfMonth) + "/" + String.format("%02d", 1 + month) + "/" + year;
                        date1.setText(s);
                    }
                }, year, month, day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
                Calendar cal = Calendar.getInstance();
            }
        });
        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String s = String.format("%02d", dayOfMonth) + "/" + String.format("%02d", 1 + month) + "/" + year;
                        date1.setText(s);
                    }
                }, year, month, day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
                Calendar cal = Calendar.getInstance();
            }
        });
    }

    private void initialize() {
        amount = findViewById(R.id.amount);
        rate = findViewById(R.id.interest_rate);
        date1 = findViewById(R.id.first_date);

        date2 = findViewById(R.id.last_date);
        date = findViewById(R.id.date_card);
        days = findViewById(R.id.days_card);

        amo = findViewById(R.id.init_amount_card);
        interest = findViewById(R.id.initerest_card);
        totalAmount = findViewById(R.id.total_amount_card);

        submit = findViewById(R.id.submit);
        checkBox = findViewById(R.id.checkbox);
    }
}

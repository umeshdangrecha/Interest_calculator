package com.umesh.patidar.interestcalculator;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText amount, rate;
    private TextView date1, date2, date, days, amo, interest, totalAmount;
    private Button submit;
    private CheckBox checkBox;
    private View v1, v2, v3, v4;


    @RequiresApi(api = Build.VERSION_CODES.O)
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

        submit.setOnClickListener(v -> submit());


        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, AlertDialog.THEME_HOLO_LIGHT, (view, year1, month1, dayOfMonth) -> {
                    String s1 = String.format("%02d", dayOfMonth) + "/" + String.format("%02d", 1 + month1) + "/" + year1;
                    date1.setText(s1);
                }, year, month, day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
            }
        });
        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, AlertDialog.THEME_HOLO_LIGHT, (view, year12, month12, dayOfMonth) -> {
                    String s12 = String.format("%02d", dayOfMonth) + "/" + String.format("%02d", 1 + month12) + "/" + year12;
                    date2.setText(s12);
                }, year, month, day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void submit() {
        String d1 = date1.getText().toString();
        String d2 = date2.getText().toString();
        String[] d1string = d1.split("/");
        String[] d2string = d2.split("/");

        LocalDate dd1 = LocalDate.parse(d1string[2] + '-' + d1string[1] + '-' + d1string[0]);
        LocalDate dd2 = LocalDate.parse(d2string[2] + '-' + d2string[1] + '-' + d2string[0]);
        Period period = Period.between(dd1, dd2);
        int d = period.getDays();
        int m = period.getMonths();
        int y = period.getYears();
        if (d < 0)
            d *= -1;
        if (m < 0)
            m *= -1;
        if (y < 0)
            y *= -1;
        String amountString = amount.getText().toString();
        String rateString = rate.getText().toString();
        int amountValue = 0;
        double rateValue = 0;

        if (!amountString.isEmpty()) {
            amountValue = Integer.parseInt(amountString);
        }
        if (!rateString.isEmpty()) {
            rateValue = Double.parseDouble(rateString);
        }

        double interestValue = 0;

        if (checkBox.isChecked()) {

            int demoAmount = amountValue;
            if (y > 0) {
                while (y-- > 0) {
                    demoAmount += 12 * (demoAmount * rateValue / 100);
                }
            }
            double interestPerMonth = demoAmount * rateValue / 100;
            double interestPerDay = interestPerMonth / 30;
            interestValue = m * interestPerMonth + d * interestPerDay;
            demoAmount += interestValue;
            interestValue = demoAmount - amountValue;

            date.setText("दिनांक " + d1 + " से " + d2 + " तक");
            days.setText("कुल " + y + " साल " + m + " महीने " + d + " दिन");
            amo.setText("मूल : " + amountValue);
            interest.setText("कुल ब्याज : " + interestValue);
            totalAmount.setText("कुल रूपए : " + (amountValue + interestValue));


        } else {
            double interestPerMonth = amountValue * rateValue / 100;
            double interestPerDay = interestPerMonth / 30;
            interestValue = (y * 12 + m) * interestPerMonth + d * interestPerDay;

            date.setText("दिनांक " + d1 + " से " + d2 + " तक");
            days.setText("कुल " + y + " साल " + m + " महीने " + d + " दिन");
            amo.setText("मूल : " + amountValue);
            interest.setText("कुल ब्याज : " + String.format("%.02f", interestValue));
            totalAmount.setText("कुल रूपए : " + String.format("%.02f", (amountValue + interestValue)));
        }
        v1.setBackgroundColor(Color.rgb(237, 0, 0));
        v2.setBackgroundColor(Color.rgb(237, 0, 0));
        v3.setBackgroundColor(Color.rgb(237, 0, 0));
        v4.setBackgroundColor(Color.rgb(237, 0, 0));


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

        v1 = findViewById(R.id.view1);
        v2 = findViewById(R.id.view2);
        v3 = findViewById(R.id.view3);
        v4 = findViewById(R.id.view4);
    }

    public void showMyBio(View view) {
        Intent intent = new Intent(this, MyDetail.class);
        startActivity(intent);
    }
}

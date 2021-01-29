package com.umesh.patidar.interestcalculator;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.umesh.patidar.interestcalculator.date.CustomDate;
import com.umesh.patidar.interestcalculator.date.DateDifference;
import com.ycuwq.datepicker.date.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainActivity
		extends AppCompatActivity {
	private static final String TAG = "abcd";
	private AdView banner_ad;
	private InterstitialAd mInterstitialAd;
	private EditText amount, rate;
	private TextView date1, date2, date, days, amo, interest, totalAmount;
	private Button submit;
	private CheckBox checkBox;
	private View v1, v2, v3, v4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		final int month = Calendar.getInstance().get(Calendar.MONTH);
		final int year = Calendar.getInstance().get(Calendar.YEAR);
		String s =
				String.format(Locale.getDefault(), "%02d", day) + "/" +
						String.format(Locale.getDefault(), "%02d",
								1 + month) +
						"/" + year;
		date1.setText(s);
		date2.setText(s);

		submit.setOnClickListener(v -> customDataDiff());


		date1.setOnClickListener(v -> dateAlert(date1));
		date2.setOnClickListener(v1 -> dateAlert(date2));
		MobileAds.initialize(this, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) {
			}
		});
		banner_ad = findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		banner_ad.loadAd(adRequest);


		prepareAd();
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			runOnUiThread(() -> {
				if (mInterstitialAd.isLoaded()) {
					mInterstitialAd.show();
				}
			});
		}, 60, 60, TimeUnit.SECONDS);
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

	private void customDataDiff() {
		String d1 = date1.getText().toString();
		String d2 = date2.getText().toString();
		CustomDate dateDiff = new DateDifference(d1, d2).getDifference();

		int d = dateDiff.getDay();
		int m = dateDiff.getMonth();
		int y = dateDiff.getYear();

		int z = y;
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
			days.setText("कुल " + z + " साल " + m + " महीने " + d + " दिन");
			amo.setText("मूल : " + amountValue);
			interest.setText("कुल ब्याज : " + interestValue);
			totalAmount.setText("कुल रूपए : " + (amountValue + interestValue));
		}
		else {
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

	private void dateAlert(TextView tv) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_cutom_date_picker, null);
		final DatePicker datePicker = v.findViewById(R.id.datePicker);
		datePicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
		builder.setPositiveButton("OK", (dialogInterface, i) -> {
			tv.setText(datePicker.getDate(new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())));
		});
		builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
		});
		builder.setView(v);
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	private void prepareAd() {
		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-2388449991477825/5297855378");
		mInterstitialAd.loadAd(new AdRequest.Builder().build());
	}

	public void showMyBio(View view) {
		Intent intent = new Intent(this, MyDetail.class);
		startActivity(intent);
	}
}

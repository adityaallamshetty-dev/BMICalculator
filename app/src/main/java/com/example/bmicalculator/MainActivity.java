package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llMain;
    private MaterialCardView resultCard;
    private TextView textViewResult;
    private TextInputEditText editTextWeight;
    private TextInputEditText editTextHeightFt;
    private TextInputEditText editTextHeightIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        MaterialButton switchToNext = findViewById(R.id.switchToNext);
        MaterialButton openStepTracker = findViewById(R.id.openStepTracker);
        MaterialButton calculateButton = findViewById(R.id.calculateButton);

        textViewResult = findViewById(R.id.textViewResult);
        editTextWeight = findViewById(R.id.editTextweight);
        editTextHeightFt = findViewById(R.id.ediTextheight);
        editTextHeightIn = findViewById(R.id.edittexthightIn);
        llMain = findViewById(R.id.llMain);
        resultCard = findViewById(R.id.resultCard);

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        switchToNext.setOnClickListener(view -> startActivity(intent));
        openStepTracker.setOnClickListener(view -> {
            Intent trackerIntent = new Intent(MainActivity.this, StepTrackerActivity.class);
            startActivity(trackerIntent);
        });
        calculateButton.setOnClickListener(view -> calculateAndShowBmi());
    }

    private void calculateAndShowBmi() {
        String wtInput = readInput(editTextWeight);
        String ftInput = readInput(editTextHeightFt);
        String inInput = readInput(editTextHeightIn);

        if (wtInput.isEmpty() || ftInput.isEmpty() || inInput.isEmpty()) {
            Toast.makeText(this, "Please enter weight, feet, and inches.", Toast.LENGTH_SHORT).show();
            return;
        }

        double weightKg;
        int feet;
        int inches;
        try {
            weightKg = Double.parseDouble(wtInput);
            feet = Integer.parseInt(ftInput);
            inches = Integer.parseInt(inInput);
        } catch (NumberFormatException exception) {
            Toast.makeText(this, "Please enter valid numeric values.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (weightKg <= 0 || feet < 0 || inches < 0 || inches > 11 || (feet == 0 && inches == 0)) {
            Toast.makeText(this, "Check the values. Inches must be between 0 and 11.", Toast.LENGTH_SHORT).show();
            return;
        }

        int totalInches = (feet * 12) + inches;
        double totalMeters = (totalInches * 2.54) / 100.0;
        double bmi = weightKg / (totalMeters * totalMeters);
        String bmiLabel = String.format(Locale.getDefault(), "%.1f", bmi);

        if (bmi >= 25.0) {
            textViewResult.setText("BMI " + bmiLabel + " - You are overweight.");
            applyResultStyle(R.color.coloreuw, R.color.bmi_over_text);

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(R.drawable.ic_fitness_logo)
                    .setTitle("Health Tip")
                    .setMessage("Your BMI is " + bmiLabel + ". Consider consulting a doctor.");
            builder.create().show();
        } else if (bmi < 18.5) {
            textViewResult.setText("BMI " + bmiLabel + " - You are underweight.");
            applyResultStyle(R.color.coloreow, R.color.bmi_under_text);
        } else {
            textViewResult.setText("BMI " + bmiLabel + " - You are in a healthy range.");
            applyResultStyle(R.color.coloreh, R.color.bmi_healthy_text);
        }
    }

    private String readInput(TextInputEditText input) {
        if (input.getText() == null) {
            return "";
        }
        return input.getText().toString().trim();
    }

    private void applyResultStyle(int backgroundColor, int textColor) {
        resultCard.setCardBackgroundColor(getColor(backgroundColor));
        textViewResult.setTextColor(getColor(textColor));
        llMain.setBackgroundResource(R.drawable.bg_screen_gradient);
    }
}

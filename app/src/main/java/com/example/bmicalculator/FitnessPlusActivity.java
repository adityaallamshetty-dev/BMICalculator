package com.example.bmicalculator;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class FitnessPlusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fitness_plus);

        ImageButton btnBack = findViewById(R.id.btnBackFitness);
        MaterialButton btnStartTrial = findViewById(R.id.btnStartTrial);
        MaterialButton btnRestorePlan = findViewById(R.id.btnRestorePlan);

        btnBack.setOnClickListener(view -> finish());
        btnStartTrial.setOnClickListener(view ->
                Toast.makeText(this, getString(R.string.trial_started), Toast.LENGTH_SHORT).show()
        );
        btnRestorePlan.setOnClickListener(view ->
                Toast.makeText(this, getString(R.string.plan_restored), Toast.LENGTH_SHORT).show()
        );
    }
}

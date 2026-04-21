package com.example.bmicalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout);

        ImageButton btnBack = findViewById(R.id.btnBackWorkout);
        MaterialButton btnStartWalk = findViewById(R.id.btnStartWalk);
        MaterialButton btnStartRun = findViewById(R.id.btnStartRun);
        MaterialButton btnNearbyPark = findViewById(R.id.btnNearbyPark);

        btnBack.setOnClickListener(view -> finish());
        btnStartWalk.setOnClickListener(view ->
                Toast.makeText(this, getString(R.string.walk_started), Toast.LENGTH_SHORT).show()
        );
        btnStartRun.setOnClickListener(view ->
                Toast.makeText(this, getString(R.string.run_started), Toast.LENGTH_SHORT).show()
        );
        btnNearbyPark.setOnClickListener(view -> openNearbyParks());
    }

    private void openNearbyParks() {
        Intent mapsIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=parks near me")
        );
        if (mapsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapsIntent);
        } else {
            Toast.makeText(this, getString(R.string.maps_unavailable), Toast.LENGTH_SHORT).show();
        }
    }
}

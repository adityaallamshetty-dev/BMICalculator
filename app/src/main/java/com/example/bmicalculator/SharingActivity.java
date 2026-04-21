package com.example.bmicalculator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class SharingActivity extends AppCompatActivity {

    private final String summaryText = "Today I completed my health goals with BMI & step tracking.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sharing);

        ImageButton btnBack = findViewById(R.id.btnBackSharing);
        MaterialButton btnShareProgress = findViewById(R.id.btnShareProgress);
        MaterialButton btnInviteSms = findViewById(R.id.btnInviteSms);
        MaterialButton btnCopyStats = findViewById(R.id.btnCopyStats);

        btnBack.setOnClickListener(view -> finish());
        btnShareProgress.setOnClickListener(view -> shareSummary());
        btnInviteSms.setOnClickListener(view -> inviteBySms());
        btnCopyStats.setOnClickListener(view -> copyStats());
    }

    private void shareSummary() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, summaryText);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_action)));
    }

    private void inviteBySms() {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"));
        smsIntent.putExtra("sms_body", "Join me on this fitness tracker app and track daily progress.");
        if (smsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(smsIntent);
        } else {
            Toast.makeText(this, getString(R.string.no_compatible_app), Toast.LENGTH_SHORT).show();
        }
    }

    private void copyStats() {
        ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            manager.setPrimaryClip(ClipData.newPlainText("fitness_stats", summaryText));
            Toast.makeText(this, getString(R.string.stats_copied), Toast.LENGTH_SHORT).show();
        }
    }
}

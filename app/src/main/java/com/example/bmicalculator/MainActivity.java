package com.example.bmicalculator;

import static androidx.appcompat.app.AlertDialog.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        LinearLayout llMain ;
        TextView textViewResult;
        EditText editTextweight;
        EditText ediTextheight;
        EditText edittexthightIn;
        Button calculateButton;
        Button switchToNext;





        switchToNext = findViewById(R.id.switchToNext);
        textViewResult = findViewById(R.id.textViewResult);
        editTextweight = findViewById(R.id.editTextweight);
        ediTextheight = findViewById(R.id.ediTextheight);
        edittexthightIn = findViewById(R.id.edittexthightIn);
        llMain = findViewById(R.id.llMain);
        calculateButton = findViewById(R.id.calculateButton);


        Intent intent = new Intent(MainActivity.this, MainActivity2.class);


        switchToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
            }

        });






        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int wt =   Integer.parseInt(editTextweight.getText().toString());
                int ft = Integer.parseInt(ediTextheight.getText().toString());
               int htIn = Integer.parseInt(edittexthightIn.getText().toString());


               int totalIn = ft*12+htIn;
               double totalCm = totalIn*2.54;
               double totalM = totalCm/100;
               double bmi = wt/(totalM*totalM);
               if(bmi> 25) {
                   textViewResult.setText("Your BMI is " + bmi + " and you are overweight");
                   llMain.setBackgroundColor(getResources().getColor(R.color.coloreuw));


                   AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                   builder.setIcon(R.drawable.ic_launcher_foreground)
                           .setTitle("please consult a doctor").setMessage("Your BMI is " + bmi + " and you are overweight");
                   AlertDialog dialog = builder.create();
                   dialog.show();


               } else if (bmi < 18) {
                   textViewResult.setText("Yo   ur BMI is " + bmi + " and you are underweight");
                   llMain.setBackgroundColor(getResources().getColor(R.color.coloreow));
               } else {
                   textViewResult.setText("Your BMI is " + bmi + " and you are HEALTHY");
                   llMain.setBackgroundColor(getResources().getColor(R.color.coloreh));
                   
               }
            }
            });


        }
    }

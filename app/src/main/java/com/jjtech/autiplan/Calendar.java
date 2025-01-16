package com.jjtech.autiplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Calendar extends AppCompatActivity {


    private CalendarView calendarview;
    private ImageView seta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_calendar);

        calendarview = findViewById(R.id.calendarView);
        seta = findViewById(R.id.setaEsquerda);


        calendarview.setOnDateChangeListener((view, year , month, dayOfMonth) -> {
            String selectDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(Calendar.this, "Data selecionada: " + selectDate, Toast.LENGTH_SHORT).show();
        });


        seta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calendar.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });

    }
}
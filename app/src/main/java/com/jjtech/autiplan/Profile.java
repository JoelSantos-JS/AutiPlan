package com.jjtech.autiplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    private ImageView seta;
    private Button logout;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_profile);



        firebaseAuth = FirebaseAuth.getInstance();




        seta = findViewById(R.id.setaEsquerda);
        logout = findViewById(R.id.logout_button);


        seta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();;
                Toast.makeText(Profile.this, "Deslogado com sucesso" , Toast.LENGTH_SHORT).show();;
                startActivity(new Intent(Profile.this, LoginActivity.class));
                finish();
            }
        });

    }
}
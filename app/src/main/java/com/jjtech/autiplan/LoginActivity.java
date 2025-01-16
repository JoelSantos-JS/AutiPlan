package com.jjtech.autiplan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.common.returnsreceiver.qual.This;

public class LoginActivity extends AppCompatActivity {


    private Button buttonL;
    private EditText mEmail,mPassoword;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener fiAuthStateListener;
    private TextView registerEdit;
    private ImageView switchMoon;
    private Boolean isDark;
    private TextView loginLabel;
    private TextView passwordLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();
        fiAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class); // Redireciona para a tela principal
                    startActivity(intent);
                    finish();
                }
            }
        };



        mEmail = findViewById(R.id.loginUsername);
        mPassoword = findViewById(R.id.loginPassword);
        buttonL = findViewById(R.id.loginButton);
        registerEdit = findViewById(R.id.registerEdit);
        switchMoon = findViewById(R.id.icon_moon);
        loginLabel = findViewById(R.id.labelUsername);
        passwordLabel = findViewById(R.id.labelPassword);


        isDark = false;



        switchMoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDark = !isDark;

                if (isDark) {
                    loginLabel.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));
                    passwordLabel.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));
                } else {
                    loginLabel.setTextColor(ContextCompat.getColor(view.getContext(), R.color.black));
                    passwordLabel.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));

                }

                AppCompatDelegate.setDefaultNightMode(
                        isDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
                );
            }
        });


        buttonL.setOnClickListener(view -> {
            String email = mEmail.getText().toString();
            String password = mPassoword.getText().toString();

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this , task -> {
                if(task.isSuccessful()) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }else  {
                    Toast.makeText(LoginActivity.this , "Falha ao fazer login" , Toast.LENGTH_SHORT).show();
                }
            });
        });

        registerEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);


                }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(fiAuthStateListener);
    }



    @Override
    protected void  onStop() {
        super.onStop();

        firebaseAuth.removeAuthStateListener(fiAuthStateListener);
    }


}
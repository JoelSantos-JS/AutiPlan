package com.jjtech.autiplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.microedition.khronos.egl.EGL;

public class RegisterActivity extends AppCompatActivity {


    private Button registerButton;
    private EditText rEmail;
    private EditText rPassword;
    private TextView gLogin;
    private EditText rName;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getSupportActionBar().hide();
            setContentView(R.layout.activity_register);


            firebaseAuth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance();




            registerButton = (Button) findViewById(R.id.registerButton);
            rEmail = (EditText) findViewById(R.id.registerEmail);
            rPassword = (EditText) findViewById(R.id.registerPassword);
            gLogin = (TextView) findViewById(R.id.registerEdit);
            rName = (EditText) findViewById(R.id.registerNome);


            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    final String name = rName.getText().toString();
                    final String email = rEmail.getText().toString();
                    final String password = rPassword.getText().toString();


                    if(name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(RegisterActivity.this, "Prencha todos os campos" , Toast.LENGTH_SHORT);
                        return;
                   }


                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            String userId = user.getUid();


                            DatabaseReference usersReference  = firebaseDatabase.getReference("users");
                            usersReference.child(userId).child("name").setValue(name);
                            usersReference.child(userId).child("email").setValue(email).addOnSuccessListener(aVoid -> {
                                    Toast.makeText(RegisterActivity.this , "Registro feito com sucesso" , Toast.LENGTH_SHORT);
                                    startActivity(new Intent(RegisterActivity.this , LoginActivity.class));
                                    finish();
                            }).addOnFailureListener(e -> {
                                Toast.makeText(RegisterActivity.this , "Erro ao salvar dados ", Toast.LENGTH_SHORT);
                            });
                        }else  {
                            Toast.makeText(RegisterActivity.this, "Falha ao registro" ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                }
            });


    }

    @Override
    protected void onStart() {
        super.onStart();





    }


    @Override
    protected void onStop() {
        super.onStop();
    }
}
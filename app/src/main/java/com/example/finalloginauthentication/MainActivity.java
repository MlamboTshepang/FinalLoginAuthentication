package com.example.finalloginauthentication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.ims.RegistrationManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity
{

    private EditText emailTxf, passwordTxf;
    private Button regBtn;
    private ProgressBar progressBar;

    // This gets added after connection to the database. Then a light The system builds.
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        initializeHooks(); // "Hooks" are created put them in a folder

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            } // Runs RegisterNewUser()
        });
    }

    private void registerNewUser()
    {
        progressBar.setVisibility(View.VISIBLE);
        String email, password;
        email = emailTxf.getText().toString();
        password = passwordTxf.getText().toString();

        // Checks if email textbox is empty or not
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(), "Please May You Enter Your Email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_LONG).show();
            return;
        }

        // This is what checks emails and passwords
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    // The ".jetbrains.annotations.NotNull" is my IDE and it is causing me issues
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task)
                    {
                        //sucessful task
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "Registration Sucessful", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

//                            Intent intent = new Intent(Login.this, DashboardActivity.class);
//                            startActivity(intent);

                        }

                        else
                            {
                                Toast.makeText(getApplicationContext(), "Registration Failed! Please try again", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                    }
                });
    }

    //once again this line is from my IDE
    @SuppressLint("WrongViewCast")


    private  void initializeHooks()
    {
        emailTxf = findViewById(R.id.txfEmail);
        passwordTxf = findViewById(R.id.txfPassword);
        regBtn = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.pbProgress);
    }
}
package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText etxtRegEmail, etxtRegUsername, etxtRegPassword, etxtRegFullname;
    private Button btnRegRegister, btnRegBack;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        btnRegRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etxtRegEmail.getText().toString();
                String username = etxtRegUsername.getText().toString();
                String password = etxtRegPassword.getText().toString();
                String fullname = etxtRegFullname.getText().toString();

                if (dbHelper.addToTable(email, username, password, fullname)) {
                    Toast.makeText(RegisterActivity.this, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Sikertelen regisztráció!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        });

        etxtRegEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String email = etxtRegEmail.getText().toString();
                if (dbHelper.checkEmailIfExist(email)) {
                    etxtRegEmail.setError("E-mail cim már létezik!");
                    etxtRegEmail.setTextColor(Color.RED);
                }
                else {
                    if (email.isEmpty()) {
                        etxtRegEmail.setError("E-mail cim megadása kötelező!");
                    } else if (!isEmail(email)) {
                        etxtRegEmail.setError("Nem megfelelő e-mail formátum!");
                    } else {
                        etxtRegEmail.setTextColor(Color.GREEN);
                    }
                }
                canRegister();
            }
        });

        etxtRegUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String username = etxtRegUsername.getText().toString();
                if (dbHelper.checkUsernameIfExist(username)) {
                    etxtRegUsername.setError("Felhasználónév doglalt!");
                    etxtRegUsername.setTextColor(Color.RED);
                }
                else {
                    if (username.isEmpty()) {
                        etxtRegUsername.setError("Felhasználónév kötelező!");
                    }
                    else {
                        etxtRegUsername.setTextColor(Color.RED);
                    }
                }
                canRegister();
            }
        });
        

    }

    private void canRegister() {
        String email = etxtRegEmail.getText().toString();
        String username = etxtRegUsername.getText().toString();
        String password = etxtRegPassword.getText().toString();
        String fullname = etxtRegFullname.getText().toString();

        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || fullname.isEmpty()) {
            btnRegRegister.setEnabled(false);
        }
        else {
            btnRegRegister.setEnabled(true);
        }
    }

    private boolean isEmail(String email) {
        Pattern regexPattern = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$");
        Matcher matcher = regexPattern.matcher(email);
        return matcher.matches();
    }

    private boolean isFullname(String fullname) {
        return fullname.split(" ").length > 1;
    }

    private void init() {
        etxtRegEmail = findViewById(R.id.etxtRegEmail);
        etxtRegUsername = findViewById(R.id.etxtRegUsername);
        etxtRegPassword = findViewById(R.id.etxtRegPassword);
        etxtRegFullname = findViewById(R.id.etxtRegFullname);
        btnRegRegister = findViewById(R.id.btnRegRegister);
        btnRegBack = findViewById(R.id.btnRegBack);
        dbHelper = new DBHelper(this);
        btnRegRegister.setEnabled(false);
    }
}
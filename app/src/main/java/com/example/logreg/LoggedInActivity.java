package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {

    private TextView txt1;
    private Button btnLogg;
    private DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();

        btnLogg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoggedInActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void init() {
        txt1 = findViewById(R.id.txtLoggedInfo);
        btnLogg = findViewById(R.id.btnLoggedBack);
        dbHelper = new DBHelper(this);

        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int id = sharedPreferences.getInt("id", -1);

        }

    }

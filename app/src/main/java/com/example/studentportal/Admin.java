package com.example.studentportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Admin extends AppCompatActivity {

    FloatingActionButton add,view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        add = findViewById(R.id.add_data_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddData();
            }
        });
        view = findViewById(R.id.view_data_btn);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewData();
            }
        });


    }

    private void openViewData() {

        Intent  intent = new Intent(getApplicationContext(), Datashow.class);
        startActivity(intent);
    }

    private void openAddData()
    {
        Intent  intent = new Intent(getApplicationContext(),add_data.class);
        startActivity(intent);
    }

}
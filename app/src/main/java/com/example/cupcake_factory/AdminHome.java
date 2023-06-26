package com.example.cupcake_factory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        Button adminButton1 = findViewById(R.id.adminButton1);
        Button adminButton2 = findViewById(R.id.adminButton2);
        Button adminButton3 = findViewById(R.id.adminButton3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        adminButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, Admin_UserProfiles.class);
                startActivity(intent);
            }
        });

        adminButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, Admin_ManageCategories.class);
                startActivity(intent);
            }
        });

        adminButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, Admin_ProcessOrders.class);
                startActivity(intent);
            }
        });
    }
}
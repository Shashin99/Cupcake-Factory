package com.example.cupcake_factory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Login extends AppCompatActivity{

    private EditText _txtUser, _txtPass;
    private Button _btnLogin, _btnCreateAccount;
    private Spinner _spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _txtUser = (EditText) findViewById(R.id.edtUser);
        _txtPass =  (EditText)  findViewById(R.id.edtPassword);
        _btnLogin = (Button) findViewById(R.id.btnLogin);
        _btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        _spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);

        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = _spinner.getSelectedItem().toString();

                if (_txtUser.getText().toString().equals("Admin") && _txtPass.getText().toString().equals("admin") && item.equals("Admin")) {

                    Intent intent = new Intent(Login.this, AdminHome.class);
                    startActivity(intent);

                } else if (_txtUser.getText().toString().equals("User") && _txtPass.getText().toString().equals("user") && item.equals("User")) {

                    Intent intent = new Intent(Login.this, UserHome.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect UserName or Password", Toast.LENGTH_LONG).show();
                }
            }

        });

        _btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(Login.this, UserRegister.class);
                startActivity(intent);
            }
        });
    }
}
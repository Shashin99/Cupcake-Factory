package com.example.cupcake_factory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegister extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPhone, editTextPostalAddress, editTextPassword1, editTextPassword2;
    private Button buttonRegister, buttonClear;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Find views by their IDs
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPostalAddress = findViewById(R.id.editTextPostalAddress);
        editTextPassword1 = findViewById(R.id.editTextPassword1);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonClear = findViewById(R.id.buttonClear);

        // Set click listener for Register button
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input values
                String username = editTextUsername.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String postalAddress = editTextPostalAddress.getText().toString().trim();
                String password1 = editTextPassword1.getText().toString().trim();
                String password2 = editTextPassword2.getText().toString().trim();

                // Perform validation
                if (username.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                        postalAddress.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
                    // Show an error message if any field is empty
                    // You can implement your own error handling here
                    return;
                }

                if (!password1.equals(password2)) {
                    // Show an error message if passwords do not match
                    // You can implement your own error handling here
                    return;
                }

                // Create a new user object
                User user = new User(username, email, phone, postalAddress, password1, password2);

                // Generate a unique key for the new user
                String userId = databaseReference.child("users").push().getKey();

                // Insert the user object into the database
                databaseReference.child("users").child(userId).setValue(user);

                // Clear the input fields
                clearFields();

                Intent intent = new Intent(UserRegister.this, Login.class);
                startActivity(intent);
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Clear the input fields
                clearFields();

            }
        });

    }

    private void clearFields() {
        editTextUsername.setText("");
        editTextEmail.setText("");
        editTextPhone.setText("");
        editTextPostalAddress.setText("");
        editTextPassword1.setText("");
        editTextPassword2.setText("");
    }
}

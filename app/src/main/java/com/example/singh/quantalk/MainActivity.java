package com.example.singh.quantalk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String key_email = "email";
    private static final String key_password = "password";
    private Button button;
    private EditText editTextemail;
    private EditText editTextpassword;
    private TextView textView;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        button = (Button) findViewById(R.id.button);
        editTextemail = (EditText) findViewById(R.id.editTextemail);
        editTextpassword = (EditText) findViewById(R.id.editTextpassword);
        textView = (TextView) findViewById(R.id.textView);
        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(this);
        textView.setOnClickListener(this);

    }

    private void registerUser() {
        String email = editTextemail.getText().toString();
        String password = editTextpassword.getText().toString();
        Map<String,Object> register= new HashMap<>();
        register.put(key_email,email);
        register.put(key_password,password);
        db.collection("User").document("users").set(register);
        if (TextUtils.isEmpty(email)) {
            System.out.println("enter email");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            System.out.println("enter password");
            return;
        }

        progressDialog.setMessage("Being registered");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), Navdrawer.class));
                } else {
                    Toast.makeText(MainActivity.this, "unsuccessful", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }


    private void signin() {

    }

    @Override
    public void onClick(View view) {
        if (view == button) {
            registerUser();

        }
        if (view == textView) {
            finish();
            startActivity(new Intent(this, Login.class));
        }

    }


}

package com.example.singh.quantalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;

    private TextView textViewemail;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,Login.class));
        }
        FirebaseUser user =firebaseAuth.getCurrentUser();
textViewemail = (TextView)findViewById(R.id.editTextemail);
textViewemail.setText("welcome  "+user.getEmail());
button=(Button)findViewById(R.id.button);
button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==button){
            firebaseAuth.signOut();
            startActivity(new Intent(this,Login.class));
    }}
}

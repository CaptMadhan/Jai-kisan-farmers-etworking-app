package com.example.jaikisan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Button kisanButton,consumerButton;
    ImageView coverPhoto;

    CardView kisanCardView,consumerCardView;

    EditText kisanPhoneNumber,kisanPassword;
    Button kisanLoginButton,kisanCreateButton;
    TextView kisanForgotPasswordText,consumerForgotPasswordText;

    EditText consumerPhoneNumber,consumerPassword;
    Button consumerLoginButton,consumerCreateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        coverPhoto = findViewById(R.id.coverPhoto);
        kisanButton =findViewById(R.id.FarmerButton);
        consumerButton =findViewById(R.id.ConsumerButton);

        kisanCardView=findViewById(R.id.KisanCardView);
        consumerCardView=findViewById(R.id.ConsumerCardView);

        kisanLoginButton=findViewById(R.id.KisanLoginButton);
        kisanPhoneNumber=findViewById(R.id.KisanPhoneNumber);
        kisanPassword=findViewById(R.id.KisanPassword);
        kisanCreateButton=findViewById(R.id.KisanCreateAccount);
        kisanForgotPasswordText=findViewById(R.id.KisanResetPassword);

        consumerLoginButton=findViewById(R.id.ConsumerLoginButton);
        consumerPhoneNumber=findViewById(R.id.ConsumerPhoneNumber);
        consumerPassword=findViewById(R.id.ConsumerPassword);
        consumerCreateButton=findViewById(R.id.ConsumerCreateAccount);
        consumerForgotPasswordText=findViewById(R.id.ConsumerResetPassword);

    }

    public void FarmerLogin(View view) {
        consumerCardView.setVisibility(View.GONE);
        kisanCardView.setVisibility(View.VISIBLE);
    }

    public void ConsumerLogin(View view) {
        consumerCardView.setVisibility(View.VISIBLE);
        kisanCardView.setVisibility(View.GONE);
    }

    public void KisanLoginButtonOnClickListener(View view) {
    }

    public void KisanCreateAccountButtonOnClickListener(View view) {
    }

    public void KisanForgortPasswordOnClickListener(View view) {
    }


    public void ConsumerLoginOnClickListener(View view) {
    }

    public void ConsumerCreateAccOnClickListener(View view) {
    }

    public void consumerForgotPasswordOnClickListener(View view) {
    }
}
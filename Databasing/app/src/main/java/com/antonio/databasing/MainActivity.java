package com.antonio.databasing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etUsername, etPassword;
    TextView tvCreateAccount;
    Button btnLogin;
    String username, password, name, gender;

    DbHelper db;
    int formsuccess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);

        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                formsuccess = 2;
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if (username.equals("")){
                    etUsername.setError("This Field is Required");
                    formsuccess--;
                }

                if (password.equals("")){
                    etPassword.setError("This Field is Required");
                    formsuccess--;
                }
                if (formsuccess == 2){
                    HashMap<String, String> map_user = new HashMap();
                    map_user.put(db.TBL_USER_USERNAME, username);
                    map_user.put(db.TBL_USER_PASSWORD, password);
                    if(db.checkUser(map_user) > 0){
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        etUsername.setError("Samok Kaayo Ni");

                    }

                }
                break;

            case R.id.tvCreateAccount:


                Intent signup = new Intent(this, SignupActivity.class);
                startActivity(signup);


                break;
        }
    }
}
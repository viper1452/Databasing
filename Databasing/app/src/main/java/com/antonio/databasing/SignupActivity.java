package com.antonio.databasing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    EditText etUN, etPW, etCPW, etFN;
    String username, password, cpassword, fullname, name, gender;

    DbHelper db;
    int formsuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DbHelper(this);

        etUN = findViewById(R.id.etUN);
        etPW = findViewById(R.id.etPW);
        etCPW = findViewById(R.id.etCPW);
        etFN = findViewById(R.id.etFN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_cancel, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnSave:

                formsuccess = 5;
                username = etUN.getText().toString();
                password = etPW.getText().toString();
                cpassword = etCPW.getText().toString();
                fullname = etFN.getText().toString();

                if (username.equals("")) {
                    etUN.setError("This Field is Required");
                    formsuccess--;
                }
                if (password.equals("")) {
                    etPW.setError("This Field is Required");
                    formsuccess--;
                }
                if (cpassword.equals("")){
                    etCPW.setError("This Field is Required");
                    formsuccess--;
                }
                if(!cpassword.equals(password)){
                    etCPW.setError("Password Mismatch");
                    formsuccess--;
                }


                if (fullname.equals("")) {
                    etFN.setError("This Field is Required");
                    formsuccess--;
                }
                if (formsuccess == 5){
                    HashMap<String, String> map_user = new HashMap();
                    map_user.put(db.TBL_USER_USERNAME, username);
                    map_user.put(db.TBL_USER_PASSWORD, password);
                    map_user.put(db.TBL_USER_NAME, name);
                    map_user.put(db.TBL_USER_GENDER, gender);

                    if(db.addUser(map_user) > 0){
                        etUN.setError("Username Already Taken </3");
                    }
                    else {
                        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                        this.finish();

                    }

                }


                break;
            case R.id.btnCancel:

                this.finish();


                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
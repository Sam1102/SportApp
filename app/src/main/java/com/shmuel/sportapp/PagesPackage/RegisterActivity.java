package com.shmuel.sportapp.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.shmuel.sportapp.UtilsPackage.EmailPasswordValidator;
import com.shmuel.sportapp.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvLogin;
    private EditText email, password, passwordConfirm;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private static final String TAG = "Check1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initUI();
        initListeners();
    }

    private void initUI() {
        tvLogin = findViewById(R.id.tvLogin);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.passwordConfirm);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
    }

    private void initListeners() {
        tvLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private void registerFirebase() {
        String emailEt = email.getText().toString();
        String passwordEt = password.getText().toString();
        String passwordConfirmEt = passwordConfirm.getText().toString();
        if (!EmailPasswordValidator.getInstance().isValidEmail(emailEt) && !EmailPasswordValidator.getInstance().isValidPassword(passwordEt)) {
            Toast.makeText(this, "The email and the password are incorrect", Toast.LENGTH_LONG).show();
        } else if (!EmailPasswordValidator.getInstance().isValidEmail(emailEt)) {
            Toast.makeText(this, "The email is incorrect", Toast.LENGTH_LONG).show();
        } else if (!EmailPasswordValidator.getInstance().isValidPassword(passwordEt)) {
            Toast.makeText(this, "The password is incorrect", Toast.LENGTH_LONG).show();
        } else if (!EmailPasswordValidator.getInstance().isValidPassword(passwordConfirmEt)) {
            Toast.makeText(this, "The password confirm is incorrect", Toast.LENGTH_LONG).show();
        } else if (!passwordEt.equals(passwordConfirmEt)) {
            Toast.makeText(this, "The passwords are not equals", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(emailEt, passwordEt)
                    .addOnCompleteListener(RegisterActivity.this, task -> {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "createUserWithEmail:success");
                            Intent intent = new Intent(RegisterActivity.this, InformationActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.i(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLogin:
                onBackPressed();
                break;
            case R.id.btnRegister:
                registerFirebase();
                break;
        }
    }

}

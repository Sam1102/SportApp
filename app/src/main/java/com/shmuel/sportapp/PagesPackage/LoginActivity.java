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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvRegister;
    private EditText email, password;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "Check1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
        initListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void initUI() {
        tvRegister = findViewById(R.id.tvRegister);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() != null) {
                startActivity(new Intent(LoginActivity.this, InformationActivity.class));
                finish();
            }
        };
    }

    private void initListeners() {
        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    private void loginFirebase() {
        String emailEt = email.getText().toString();
        String passwordEt = password.getText().toString();
        if (!EmailPasswordValidator.getInstance().isValidEmail(emailEt) && !EmailPasswordValidator.getInstance().isValidPassword(passwordEt)) {
            Toast.makeText(this, "The email and the password are incorrect", Toast.LENGTH_LONG).show();
        } else if (!EmailPasswordValidator.getInstance().isValidEmail(emailEt)) {
            Toast.makeText(this, "The email is incorrect", Toast.LENGTH_LONG).show();
        } else if (!EmailPasswordValidator.getInstance().isValidPassword(passwordEt)) {
            Toast.makeText(this, "The password is incorrect", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(emailEt, passwordEt)
                    .addOnCompleteListener(LoginActivity.this, task -> {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "signInWithEmail:success");
                            Intent intent = new Intent(LoginActivity.this, InformationActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.i(TAG, "singInWithEmail:Fail");
                            Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRegister:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLogin:
                loginFirebase();
                break;
        }
    }

}

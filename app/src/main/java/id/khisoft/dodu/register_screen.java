package id.khisoft.dodu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.khisoft.dodu.Controller.AkunController;

public class register_screen extends AppCompatActivity {
    private AkunController ac;
    private EditText etUsername,etEmail,etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        ac = new AkunController(this);

        TextView tvLoginScreen = findViewById(R.id.tvLoginScreen);
        tvLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.Register(etUsername.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
            }
        });

    }
}

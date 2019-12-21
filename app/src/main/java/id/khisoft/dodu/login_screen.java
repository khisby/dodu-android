package id.khisoft.dodu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.khisoft.dodu.Controller.AkunController;

public class login_screen extends AppCompatActivity {
    private AkunController ac;
    private EditText etSurel, etSandi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        ac = new AkunController(this);

        TextView tvRegisterScreen = findViewById(R.id.tvRegisterScreen);
        Button btnLogin = findViewById(R.id.btnLogin);
        etSurel = findViewById(R.id.etEmail);
        etSandi = findViewById(R.id.etPassword);

        tvRegisterScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), register_screen.class);
                startActivity(i);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.Login(etSurel.getText().toString(),etSandi.getText().toString());
            }
        });
    }
}

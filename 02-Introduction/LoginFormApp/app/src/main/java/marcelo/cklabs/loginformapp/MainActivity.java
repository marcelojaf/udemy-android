package marcelo.cklabs.loginformapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        EditText txtLogin = (EditText) findViewById(R.id.txtLogin);
        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(view -> {
            Log.i("Login", txtLogin.getText().toString() );
            Log.i("Password", txtPassword.getText().toString());
        });
    }
}
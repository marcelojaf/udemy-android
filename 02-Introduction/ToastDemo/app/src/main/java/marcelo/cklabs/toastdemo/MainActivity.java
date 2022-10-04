package marcelo.cklabs.toastdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtName = (EditText) findViewById(R.id.txtName);
        Button btnClickMe = (Button) findViewById(R.id.btnClickMe);

        String defaultToastMessage = "Hello ";

        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, defaultToastMessage + txtName.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
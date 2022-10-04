package marcelo.cklabs.exampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtName = (EditText)findViewById(R.id.txtName);

        Button btnMainButton = (Button)findViewById(R.id.btnMainButton);
        btnMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Info", "Button pressed!");
                Log.i("Values",txtName.getText().toString());
            }
        });
    }
}
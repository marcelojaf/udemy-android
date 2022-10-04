package marcelo.cklabs.currencyconverter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final double convertRate = 5.65;
    private double amountToBeConverted = 0;
    private double convertedAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConvert = (Button) findViewById(R.id.btnConvert);
        EditText txtAmount = (EditText) findViewById(R.id.txtAmount);

        Locale brazil = new Locale("pt","BR");
        btnConvert.setOnClickListener(view -> {
            amountToBeConverted = Double.parseDouble(txtAmount.getText().toString());
            convertedAmount = amountToBeConverted * convertRate;
            Toast.makeText(MainActivity.this,
                    getString(R.string.ConvertedCurrencyMessage) + String.format(brazil, "%.2f", convertedAmount),
                    Toast.LENGTH_LONG).show();
        });
    }
}
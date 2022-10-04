package marcelo.cklabs.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lstViewDefault = findViewById(R.id.lstViewDefault);

        ArrayList<String> myFamily = new ArrayList<>();
        myFamily.add("Marcelo");
        myFamily.add("Sabrina");
        myFamily.add("Enzo");
        myFamily.add("Wilson");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myFamily);

        lstViewDefault.setAdapter(arrayAdapter);

        lstViewDefault.setOnItemClickListener((adapterView, view, i, l) ->
                Toast.makeText(getApplicationContext(), "Person Selected: " + myFamily.get(i), Toast.LENGTH_LONG).show());
    }
}
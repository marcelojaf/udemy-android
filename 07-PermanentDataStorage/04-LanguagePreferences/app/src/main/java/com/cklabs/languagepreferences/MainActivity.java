package com.cklabs.languagepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public SharedPreferences sharedPreferences;
    TextView lblLanguage;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        int itemId  = item.getItemId();
        if (itemId == R.id.english) {
            setLanguage("English");
            return true;
        } else if (itemId == R.id.spanish) {
            setLanguage("Spanish");
            return true;
        } else return false;
    }

    private void setLanguage(String language) {
        sharedPreferences.edit().putString("language", language).apply();
        lblLanguage.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblLanguage = findViewById(R.id.lblLanguage);
        sharedPreferences = this.getSharedPreferences("com.cklabs.languagepreferences", Context.MODE_PRIVATE);

        String actualLanguage = sharedPreferences.getString("language", "Error");

        if (actualLanguage.equals("Error")){
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Choose a language")
                    .setMessage("Which language would you like to use?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Set English
                            setLanguage("English");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Set Spanish
                            setLanguage("Spanish");
                        }
                    })
                    .show();
        } else {
            lblLanguage.setText(actualLanguage);
        }
    }


}
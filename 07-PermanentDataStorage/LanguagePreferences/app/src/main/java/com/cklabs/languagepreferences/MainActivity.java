package com.cklabs.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final String PREFERENCE_LANGUAGE = "language";
    private final String PREFERENCE_ENGLISH = "English";
    private final String PREFERENCE_SPANISH = "Spanish";
    SharedPreferences sharedPreferences;
    TextView txtMain;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.english:
                setLanguage(PREFERENCE_ENGLISH);
                return true;
            case R.id.spanish:
                setLanguage(PREFERENCE_SPANISH);
                return true;
            default:
                return false;
        }
    }


    public void setLanguage(String language) {
        sharedPreferences.edit().putString(PREFERENCE_LANGUAGE, language).apply();
        txtMain.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.cklabs.languagepreferences", Context.MODE_PRIVATE);
        txtMain = findViewById(R.id.txtMain);

        String language = sharedPreferences.getString(PREFERENCE_LANGUAGE, "Error");

        if (language.equals("Error")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Choose a language")
                    .setMessage("Which language would you like to use?")
                    .setPositiveButton(PREFERENCE_ENGLISH, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage(PREFERENCE_ENGLISH);
                        }
                    })
                    .setNegativeButton(PREFERENCE_SPANISH, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage(PREFERENCE_SPANISH);
                        }
                    })
                    .show();
        } else {
            txtMain.setText(language);
        }
    }
}
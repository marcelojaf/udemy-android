package com.cklabs.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.cklabs.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> friends = new ArrayList<String>();
        friends.add("Edilson");
        friends.add("Lys");
        friends.add("Froes");

        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();

            Log.i("#### friends: ", ObjectSerializer.serialize(friends));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> newFriends = new ArrayList<>();
        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Log.i("#### New friends: ", newFriends.toString());
    }
}
package marcelo.cklabs.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("marcelo.cklabs.sharedpreferences", Context.MODE_PRIVATE);

        /*ArrayList<String> friends = new ArrayList<>();
        friends.add("Bruno");
        friends.add("Lys");
        friends.add("Rafael");
        friends.add("Michel");

        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();
            Log.i(">>##>> Friends: ", ObjectSerializer.serialize(friends));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        ArrayList<String> newFriends = new ArrayList<>();
        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends",ObjectSerializer.serialize(new ArrayList<>())));
            Log.i(">>>!!!!>>>> New Friends: ", newFriends.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //sharedPreferences.edit().putString("username","marcelojaf").apply();
        //String userName = sharedPreferences.getString("username","");
        //Log.i(">> THIS IS THE USERNAME: ", userName);
    }
}
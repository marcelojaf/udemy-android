package marcelo.cklabs.whatstheweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText txtLocation;
    TextView lblWeather;
    Button btnWhatsTheWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWhatsTheWeather = findViewById(R.id.btnWhatsTheWeather);
        txtLocation = findViewById(R.id.txtLocation);
        lblWeather = findViewById(R.id.lblWeather);

        btnWhatsTheWeather.setOnClickListener(view -> {
            lblWeather.setText("");
            GetWeather();
        });
    }

    public void GetWeather(){

        try {
            DownloadTask downloadTask = new DownloadTask();
            String location = txtLocation.getText().toString();

            if (!location.isEmpty()){
                String encodedCityName = URLEncoder.encode(location.trim(), "UTF-8");

                downloadTask.execute("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=431ec7bf9ea08010f2a1b081f5fd99bc");
            } else {
                Toast.makeText(getApplicationContext(), "You need to type the name of a city!",Toast.LENGTH_SHORT).show();
            }

            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(txtLocation.getWindowToken(),0);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Could not find weather :(",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;
            } catch (Exception e) {
                e.printStackTrace();

                //Toast.makeText(getApplicationContext(), "Could not find weather :(",Toast.LENGTH_SHORT).show();

                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");

                JSONArray array = new JSONArray(weatherInfo);
                String message = "";

                for (int i=0; i < array.length(); i++) {
                    JSONObject jsonPart = array.getJSONObject(i);

                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");
                    if (!main.equals("") && !description.equals("")){
                        message += main + ": " + description + "\r\n";
                    }
                }

                if (!message.equals("")){
                    lblWeather.setText(message);
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Could not find weather :(",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }


}
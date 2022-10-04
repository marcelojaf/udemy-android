package marcelo.cklabs.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean isBart = true;
    private final int animation = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgBart = (ImageView) findViewById(R.id.imgBart);
        ImageView imgHomer = (ImageView) findViewById(R.id.imgHomer);

        //Spin Bart when the app start
        imgBart.setX(-1000);
        imgBart.animate().translationXBy(1000).rotation(3600).setDuration(2000);

        //The imgHomer will control both images since it is on the front of imgBart
        imgHomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Info", "Homer tapped!");

                switch (animation){
                    case 1:
                        if (isBart) {
                            imgBart.animate().alpha(0).setDuration(2000);
                            imgHomer.animate().alpha(1).setDuration(2000);
                            isBart = false;
                        } else {
                            imgBart.animate().alpha(1).setDuration(2000);
                            imgHomer.animate().alpha(0).setDuration(2000);
                            isBart = true;
                        }
                        break;
                    case 2:
                        imgBart.animate().translationXBy(-1200).setDuration(2000);
                        break;
                    case 3:
                        imgBart.animate().rotation(1800).alpha(0).setDuration(1000);
                        break;
                    case 4:
                        imgBart.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);
                        break;
                }
            }
        });
    }
}
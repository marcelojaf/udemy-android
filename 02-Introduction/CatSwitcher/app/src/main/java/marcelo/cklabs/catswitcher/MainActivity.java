package marcelo.cklabs.catswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //Using true for Cat1 and false for Cat2
    private boolean cat1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSwitchCat = (Button) findViewById(R.id.btnSwitchCat);
        ImageView imgCat = (ImageView) findViewById(R.id.imgCat);

        btnSwitchCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cat1 == true)
                {
                    imgCat.setImageResource(R.drawable.cat2);
                    cat1 = false;
                }
                else{
                    imgCat.setImageResource(R.drawable.cat1);
                    cat1 = true;
                }
            }
        });
    }
}
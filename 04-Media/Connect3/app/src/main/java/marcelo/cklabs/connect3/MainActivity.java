package marcelo.cklabs.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0:Yellow, 1:Red, 2:Empty
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int activePlayer = 0;
    boolean isGameActive = true;
    String winner;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        TextView txtPlayer = findViewById(R.id.txtPlayer);
        Button btnRestart = findViewById(R.id.btnRestart);

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        //Check first if the tapped space is empty, to allow a player to check that space
        if (gameState[tappedCounter] == 2) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                txtPlayer.setText(R.string.ActivePlayerRed);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                txtPlayer.setText(R.string.ActivePlayerYellow);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    if (activePlayer == 1)
                        winner = getString(R.string.YellowPlayerName);
                    else
                        winner = getString(R.string.RedPlayerName);

                    txtPlayer.setText(getString(R.string.WinnerPlayer, winner));
                    isGameActive = false;
                    btnRestart.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRestart = findViewById(R.id.btnRestart);
        TextView txtPlayer =  findViewById(R.id.txtPlayer);
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        btnRestart.setOnClickListener(view -> {
            btnRestart.setVisibility(View.INVISIBLE);
            txtPlayer.setText(R.string.InitialPlayer);
            activePlayer = 0;
            gameState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
            isGameActive = true;
            ImageView counter;
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);
            }
        });

        ImageView img11 =  findViewById(R.id.img11);
        ImageView img12 =  findViewById(R.id.img12);
        ImageView img13 = findViewById(R.id.img13);
        ImageView img21 =  findViewById(R.id.img21);
        ImageView img22 = findViewById(R.id.img22);
        ImageView img23 =  findViewById(R.id.img23);
        ImageView img31 =  findViewById(R.id.img31);
        ImageView img32 = findViewById(R.id.img32);
        ImageView img33 = findViewById(R.id.img33);

        img11.setOnClickListener(view -> {
            if (isGameActive)
                dropIn(view);
        });

        img12.setOnClickListener(view -> {
            if (isGameActive)
                dropIn(view);
        });

        img13.setOnClickListener(view -> {
            if (isGameActive)
                dropIn(view);
        });

        img21.setOnClickListener(view -> {
            if (isGameActive)
                dropIn(view);
        });

        img22.setOnClickListener(view -> {
            if (isGameActive)
                dropIn(view);
        });

        img23.setOnClickListener(view -> {
            if (isGameActive)
                dropIn(view);
        });

        img31.setOnClickListener(view -> {
            if (isGameActive)
                dropIn(view);
        });

        img32.setOnClickListener(view -> {
            if (isGameActive)
                dropIn(view);
        });

        img33.setOnClickListener(view -> {
            if (isGameActive)
                dropIn(view);
        });
    }
}
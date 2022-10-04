package marcelo.cklabs.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnGo;
    TextView txtTimer;
    TextView txtOperation;
    TextView txtScore;
    TextView txtAnswer;
    androidx.gridlayout.widget.GridLayout gridLayout;
    Button btn11;
    Button btn12;
    Button btn21;
    Button btn22;
    Button btnPlayAgain;

    ArrayList<Integer> results = new ArrayList<Integer>();
    int correctResultLocation;
    int score = 0;
    int numberOfQuestions = 0;
    boolean isGameActive = true;

    public void start(View view) {
        isGameActive = true;
        btnGo.setVisibility(View.INVISIBLE);

        txtTimer.setVisibility(View.VISIBLE);
        txtOperation.setVisibility(View.VISIBLE);
        txtScore.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);

        CountDownTimer countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                txtTimer.setText(Long.toString(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                txtTimer.setText("0s");
                isGameActive = false;
                txtAnswer.setText("FINISHED!!");
                btnPlayAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void reset(View view){
        score = 0;
        numberOfQuestions = 0;
        txtTimer.setText("30s");
        txtScore.setText(score + "/" + numberOfQuestions);
        txtAnswer.setVisibility(View.INVISIBLE);

        newQuestion();

        btnPlayAgain.setVisibility(View.INVISIBLE);

        start(view);
    }

    public void chooseAnswer(View view) {
        if (isGameActive) {
            String buttonTapped = view.getTag().toString();

            if (buttonTapped.equals(Integer.toString(correctResultLocation))) {
                txtAnswer.setText("CORRECT!");
                txtAnswer.setVisibility(View.VISIBLE);
                score++;
            } else {
                txtAnswer.setText("WRONG!");
            }
            numberOfQuestions++;

            txtScore.setText(score + "/" + numberOfQuestions);

            newQuestion();
        }
    }

    public void newQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int wrongResult;

        txtOperation.setText(a + " + " + b);

        correctResultLocation = rand.nextInt(4);

        results.clear();

        for (int i = 0; i < 4; i++) {
            if (i == correctResultLocation) {
                results.add(a + b);
            } else {
                wrongResult = rand.nextInt(41);
                while (wrongResult == a + b) {
                    wrongResult = rand.nextInt(41);
                }
                results.add(wrongResult);
            }
        }

        btn11.setText(Integer.toString(results.get(0)));
        btn12.setText(Integer.toString(results.get(1)));
        btn21.setText(Integer.toString(results.get(2)));
        btn22.setText(Integer.toString(results.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = findViewById(R.id.btnGo);
        txtTimer = findViewById(R.id.txtTimer);
        txtOperation = findViewById(R.id.txtOperation);
        txtScore = findViewById(R.id.txtScore);
        gridLayout = findViewById(R.id.gridLayout);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn21 = findViewById(R.id.btn21);
        btn22 = findViewById(R.id.btn22);
        txtAnswer = findViewById(R.id.txtAnswer);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);

        newQuestion();

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(view);
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(view);
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(view);
            }
        });

        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(view);
            }
        });

        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(view);
            }
        });

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset(view);
            }
        });
    }
}
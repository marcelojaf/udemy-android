package marcelo.cklabs.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtTime;
    SeekBar skbTimer;
    Button btnStartStop;
    CountDownTimer countDownTimer;
    boolean isTimerActive = false;

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String minutesString = Integer.toString(minutes);
        if (minutes <= 9) {
            minutesString = "0" + minutesString;
        }

        String secondsString = Integer.toString(seconds);
        if (seconds <= 9) {
            secondsString = "0" + secondsString;
        }

        txtTime.setText(minutesString + ":" + secondsString);
    }

    public void resetTimer(){
        txtTime.setText("00:30");
        skbTimer.setProgress(30);
        skbTimer.setEnabled(true);
        countDownTimer.cancel();
        btnStartStop.setText("START");
        isTimerActive = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skbTimer = findViewById(R.id.skbTimer);
        txtTime = findViewById(R.id.txtTimer);
        btnStartStop = findViewById(R.id.btnStartStop);

        int maxTime = 600;
        int startPosition = 30;

        skbTimer.setMax(maxTime);
        skbTimer.setProgress(startPosition);

        skbTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTimerActive) {
                    resetTimer();
                } else {
                    isTimerActive = true;
                    skbTimer.setEnabled(false);
                    btnStartStop.setText("STOP!");

                    countDownTimer = new CountDownTimer(skbTimer.getProgress() * 1000 + 100, 1000) {
                        @Override
                        public void onTick(long l) {
                            updateTimer((int) l / 1000);
                        }

                        @Override
                        public void onFinish() {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                            mediaPlayer.start();
                            resetTimer();
                        }
                    }.start();
                }
            }
        });


    }
}
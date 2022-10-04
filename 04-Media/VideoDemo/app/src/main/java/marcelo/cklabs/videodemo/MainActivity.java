package marcelo.cklabs.videodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView vidMain = (VideoView) findViewById(R.id.vidMain);

        vidMain.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.wilson);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vidMain);

        vidMain.setMediaController(mediaController);

        vidMain.start();
    }
}
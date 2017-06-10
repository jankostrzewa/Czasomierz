package psychofizyka.czasomierz;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class FlashScreenActivity extends AppCompatActivity {

    Button btnFlashTimerStop;
    TextView timerTextView;
    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            timerTextView.setText(String.format("%d:%02d:%02d", minutes, seconds,millis % 1000));
            timerHandler.postDelayed(this, 25);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        final View window = findViewById(R.id.flashScreenView);
        timerTextView = (TextView) findViewById(R.id.timertextview);

        btnFlashTimerStop = (Button) findViewById(R.id.btnFlashTimerStop);
        btnFlashTimerStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.isEnabled()) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setEnabled(false);
                }
            }
        });
        Random r = new Random();
        int delay = r.nextInt(10 - 3) + 3;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);
                window.setBackgroundColor(Color.WHITE);
                btnFlashTimerStop.setEnabled(true);
            }
        }, 1000 * delay);
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }
}

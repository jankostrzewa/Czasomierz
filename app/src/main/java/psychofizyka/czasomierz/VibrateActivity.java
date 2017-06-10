package psychofizyka.czasomierz;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Vibrator;

import java.util.Random;

public class VibrateActivity extends AppCompatActivity {

    Button btnVibrateTimerStop;
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
        setContentView(R.layout.activity_vibrate);

        timerTextView = (TextView) findViewById(R.id.timertextview);

        btnVibrateTimerStop = (Button) findViewById(R.id.btnVibrateTimerStop);
        btnVibrateTimerStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.isEnabled()) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setEnabled(false);
                }
            }
        });
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Random r = new Random();
        int delay = r.nextInt(10 - 3) + 3;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);
                btnVibrateTimerStop.setEnabled(true);
                vibrator.vibrate(400);
            }
        }, 1000 * delay);
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }

    private void Vibrate(){

    }
}

package psychofizyka.czasomierz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void vibrateActivity(View view) {
        Intent intent = new Intent(this, VibrateActivity.class);
        startActivity(intent);
    }

    public void soundActivity(View view) {
        Intent intent = new Intent(this, SoundActivity.class);
        startActivity(intent);
    }

    public void flashScreenActivity(View view) {
        Intent intent = new Intent(this, FlashScreenActivity.class);
        startActivity(intent);
    }

}

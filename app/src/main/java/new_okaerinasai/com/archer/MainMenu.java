package new_okaerinasai.com.archer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenu extends AppCompatActivity {
    @BindView(R.id.exitButton)
    ImageButton exitButton;

    @BindView(R.id.startButton)
    ImageButton startButton;

    @BindView(R.id.feedbackButton)
    ImageButton feedbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
        setListeners();
    }

    private void setListeners() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, DrawFromFingerActivity.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfQ97DGxcJtYzXmRSqWyTEqeFwY-7rFqF1T1FFqvsfb48aPRg/viewform?usp=sf_link"));
                startActivity(browserIntent);
                //Intent intentNow = new Intent(MainMenu.this, LessonChooseActivity.class);
                //startActivity(intentNow);
            }
        });
    }
}

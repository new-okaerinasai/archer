package tashariko.com.writetoscreenapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.turkialkhateeb.materialcolorpicker.ColorChooserDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenu extends AppCompatActivity {
    @BindView(R.id.exitButton)
    Button exitButton;

    @BindView(R.id.startButton)
    Button startButton;

    @BindView(R.id.feedbackButton)
    Button feedbackButton;

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
            }
        });
    }
}

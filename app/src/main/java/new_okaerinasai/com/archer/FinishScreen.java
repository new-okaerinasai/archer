package new_okaerinasai.com.archer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinishScreen extends AppCompatActivity {

    @BindView(R.id.returnButton)
    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_screen);
        ButterKnife.bind(this);
        returnButton.setText("Return.");
        Intent intent = getIntent();
        float score = intent.getFloatExtra("score", (float) 228.01);
        TextView textView = (TextView) findViewById(R.id.congratsWithScore);
        textView.setText(String.format("Congrats! \n Your score for this lesson is \n %.2f", score));
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

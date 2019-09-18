package tashariko.com.writetoscreenapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawFromFingerActivity extends AppCompatActivity {

    @BindView(R.id.customCanvas)
    CustomCanvasForDraw customCanvasForDraw;

    @BindView(R.id.resetButton)
    Button resetButton;

    @BindView(R.id.sizeMinusButton)
    Button sizeMinusButton;

    @BindView(R.id.sizePlusButton)
    Button sizePlusButton;

    @BindView(R.id.finishButton)
    Button finishButton;
    //@BindView(R.id.colorButton)
    //Button colorButton;

    @BindView(R.id.undoButton)
    Button undoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_from_finger);
        ButterKnife.bind(this);
        listeners();
        customCanvasForDraw.setDebugMode(true);
    }

    private void listeners() {
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("llll");
                customCanvasForDraw.setScore();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetView();
            }
        });

        sizeMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customCanvasForDraw.increaseWidth(true);
            }
        });

        sizePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customCanvasForDraw.increaseWidth(false);
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customCanvasForDraw.undoView();
            }
        });
    }

    private void resetView() {
        customCanvasForDraw.resetView();
    }
}

package new_okaerinasai.com.archer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawFromFingerActivity extends AppCompatActivity {
    private int chosenColor;
    private static final int REQUEST_CHANGE_COLOR = -1;

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

    @BindView(R.id.undoButton)
    Button undoButton;

    @BindView(R.id.dimMinus)
    Button dimMinus;

    @BindView(R.id.dimPlus)
    Button dimPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_from_finger);
        ButterKnife.bind(this);
        listeners();
        customCanvasForDraw.setDebugMode(true);
        chosenColor = Color.BLACK;
    }

    private void listeners() {
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customCanvasForDraw.setScore();
                boolean result = customCanvasForDraw.onClickNext();
                if (result) {
                    Intent intent = new Intent(DrawFromFingerActivity.this, FinishScreen.class);
                    intent.putExtra("score", (float) 76.12);
                    startActivity(intent);
                }
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

        dimMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customCanvasForDraw.increaseDim(true);
            }
        });

        dimPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customCanvasForDraw.increaseDim(false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        customCanvasForDraw.setColor(data.getIntExtra("chosenColor", Color.RED));
    }

    private void resetView() {
        customCanvasForDraw.resetView();
    }
}

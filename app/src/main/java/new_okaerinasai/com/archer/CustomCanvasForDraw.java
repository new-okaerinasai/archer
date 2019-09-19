package new_okaerinasai.com.archer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomCanvasForDraw extends RelativeLayout implements CustomDrawView.GetCoordinateCallback {

    private CustomDrawView customDrawView;
    private TextView drawScore;

    private boolean isDebugEnabled=true;

    public CustomCanvasForDraw(Context context) {
        this(context,null);
    }

    public CustomCanvasForDraw(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCanvasForDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.custom_canvas_for_draw,this,true);

        customDrawView = (CustomDrawView) findViewById(R.id.mainView);

        drawScore = (TextView) findViewById(R.id.drawScore);
        System.out.println("llllssl");
        if (isDebugEnabled) {
            drawScore.setVisibility(VISIBLE);
        } else {
            drawScore.setVisibility(GONE);
        }
        customDrawView.setThisCallback(this);

    }

    public float getScore() {
        return customDrawView.getScore();
    }

    public void setScore() {
        drawScore.setText(String.format("%.02f", getScore()));
    }

    public void setDebugMode(boolean isDebugEnabled) {
        this.isDebugEnabled=isDebugEnabled;
    }

    public void changeColor(int color) {
        customDrawView.setDrawColor(color);
    }

    public void undoView() {
        customDrawView.undoPath();
    }

    public void increaseWidth(boolean decrease) {
        customDrawView.increaseWidth(decrease);
    }

    public void resetView() {
        customDrawView.resetView();
        drawScore.setText("0.0");
    }

    @Override
    public void moving(float x, float y) {
        // drawScore.setText(String.format("%.02f", customDrawView.getScore()));
    }

    @Override
    public void start(float x, float y) {
        // startText.setText(String.format("%.02f",x)+", "+ String.format("%.02f",y));
    }

    @Override
    public void end(float x, float y) {
        // endText.setText(String.format("%.02f",x)+", "+ String.format("%.02f",y));
    }

    public void setColor(int color) {
        customDrawView.setDrawColor(color);
    }
}

package new_okaerinasai.com.archer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class CustomDrawView extends android.support.v7.widget.AppCompatImageView {
    private static final int STATE_STILL = 0;
    private static final int STATE_MOVING = 1;
    private static int DEFAULT_COLOR;

    private int state = 0;
    private ArrayList<Paint> paintPenList = new ArrayList<>();
    private Path latestPath;
    private Paint latestPaint;
    private ArrayList<Path> pathPenList = new ArrayList<>();
    private GetCoordinateCallback callbackForCoordinate;
    private int lineWidth = 15;
    private int currentColor;

    private Path intendedPath;
    private Path finalPath;

    private Paint intendedPaint;
    private ArrayList<Path> intendedPathList = new ArrayList<>();

    private int now = 0;

    private Canvas savedCanvasDone;

    private Bitmap bitmapLesson;
    private Bitmap bitmapDone;

    public CustomDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        DEFAULT_COLOR = Color.GREEN; //ContextCompat.getColor(getContext(), R.color.colorAccent);
        currentColor = Color.GREEN;
        initPaintNPen(currentColor);
        finalPath = new Path();
        Path intendedPath1 = new Path();
        Path intendedPath2 = new Path();
        Path intendedPath3 = new Path();
        Path intendedPath4 = new Path();

        //intendedPath1.addCircle(400, 400, 400, Path.Direction.CW);
        //intendedPathList.add(intendedPath1);

        //intendedPath2.addRect(200, 300, 500, 400, Path.Direction.CW);
        //intendedPathList.add(intendedPath2);

        //intendedPath3.addRect(240, 340, 540, 440, Path.Direction.CW);
        //intendedPathList.add(intendedPath3);

        //intendedPath4.addRect(320, 320, 560, 460, Path.Direction.CW);
        //intendedPathList.add(intendedPath4);

        bitmapLesson = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.lesson1);
        bitmapDone = Bitmap.createBitmap(bitmapLesson.getWidth(), bitmapLesson.getHeight(), Bitmap.Config.RGB_565);

        intendedPaint = new Paint();
        intendedPaint.setColor(Color.RED);
        intendedPaint.setStyle(Paint.Style.STROKE);
        intendedPaint.setStrokeWidth(20);

        savedCanvasDone = new Canvas();
        savedCanvasDone.setBitmap(bitmapDone);
    }

    private void initPaintNPen(int color) {

        latestPaint = getNewPaintPen(color);
        latestPath = getNewPathPen();

        paintPenList.add(latestPaint);
        pathPenList.add(latestPath);
    }

    private Path getNewPathPen() {
        Path path = new Path();
        return path;
    }

    private Paint getNewPaintPen(int color) {

        Paint mPaintPen =new Paint();

        mPaintPen.setStrokeWidth(lineWidth);
        mPaintPen.setAntiAlias(true);
        mPaintPen.setDither(true);
        mPaintPen.setStyle(Paint.Style.STROKE);
        mPaintPen.setStrokeJoin(Paint.Join.MITER);
        mPaintPen.setStrokeCap(Paint.Cap.ROUND);
        mPaintPen.setColor(color);

        return mPaintPen;

    }

    public void setThisCallback(GetCoordinateCallback callback) {
        this.callbackForCoordinate=callback;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        Log.i("CO-ordinate",event.getX()+" : "+event.getY());

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            callbackForCoordinate.start(x,y);
            startPath(x,y);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            callbackForCoordinate.moving(x,y);
            updatePath(x,y); 
        } else if (event.getAction()== MotionEvent.ACTION_UP) {
            callbackForCoordinate.end(x,y);
            endPath(x,y);
        }
        invalidate();
        return true;
    }

    private void startPath(float x, float y) {
        initPaintNPen(currentColor);
        latestPath.moveTo(x,y);
    }

    private void updatePath(float x, float y) {
        state = STATE_MOVING;
        latestPath.lineTo(x,y);
    }

    private void endPath(float x, float y) {

    }

    public void setDrawColor(int color) {
        currentColor = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapLesson, 10, 10, intendedPaint);
        for (int i = 0; i < paintPenList.size(); i++) {
            canvas.drawPath(pathPenList.get(i), paintPenList.get(i));
            savedCanvasDone.drawPath(pathPenList.get(i), paintPenList.get(i));
            finalPath.addPath(pathPenList.get(i));
        }
        /*for (int i = 0; i <= now; ++i) {
            canvas.drawPath(intendedPathList.get(i), intendedPaint);
            savedCanvas.drawPath(intendedPathList.get(i), intendedPaint);
        }*/
    }

    public void increaseWidth(boolean decrease) {
        if (decrease) {
            if (lineWidth > 5) {
                lineWidth = lineWidth - 10;
            }
        } else {
            if (lineWidth < 50) {
                lineWidth = lineWidth + 10;
            }
        }

        invalidate();
    }

    public void increaseDim(boolean decrease) {
        if (decrease) {
            currentColor = changeAlpha(currentColor, (float) 0.5);
        } else {
            currentColor = changeAlpha(currentColor, (float) 1.6);
        }
        invalidate();
    }

    public int changeAlpha(int color, float scale) {
        int alpha = Math.round(Color.alpha(color) * scale);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public void resetView() {
        currentColor = DEFAULT_COLOR;
        state = STATE_STILL;

        latestPath.reset();
        latestPaint.reset();

        pathPenList.clear();
        paintPenList.clear();

        finalPath.reset();
        lineWidth = 20;

        initPaintNPen(Color.GREEN);

        now = 0;

        invalidate();
    }


    public void undoPath() {
        if (paintPenList.size() > 1) {
            latestPaint = paintPenList.get(paintPenList.size() - 2);
            latestPath = pathPenList.get(pathPenList.size() - 2);

            paintPenList.remove(paintPenList.size() - 1);
            pathPenList.remove(pathPenList.size() - 1);

            currentColor = latestPaint.getColor();
            lineWidth = (int) latestPaint.getStrokeWidth();
        } else {
            resetView();
        }
        invalidate();
    }

    float getScore() {
        int intersection = 0;
        int union = 0;
        boolean kek = false;
        int bgcolor = bitmapLesson.getPixel(0, 0);
        int bgcolorDrawn = bitmapDone.getPixel(0, 0);
        for (int i = 0; i < bitmapLesson.getWidth(); ++i) {
            for (int j = 0; j < bitmapLesson.getHeight(); ++j) {
                int colorDone = bitmapLesson.getPixel(i, j);
                int colorDrawn = bitmapDone.getPixel(i, j);
                if (colorDone != bgcolor && colorDrawn != bgcolorDrawn) {
                    intersection += 1;
                    union += 1;
                } else if ((colorDone != bgcolor && colorDrawn == bgcolorDrawn) ||
                        (colorDone == bgcolor && colorDrawn == currentColor)) {
                    union += 1;
                }
            }
        } /*
        if (kek) {
            return (float) 0.2;
        } else {
            return  (float) 0.1;
        }*/
        return (float) intersection / union;
    }

    public interface GetCoordinateCallback {
        void moving(float x, float y);

        void start(float x, float y);

        void end(float x, float y);
    }
    public boolean onClickNext() {
        if (now < intendedPathList.size() - 1) {
            ++now;
            invalidate();
            return false;
        } else {
            return true;
        }
    }
}

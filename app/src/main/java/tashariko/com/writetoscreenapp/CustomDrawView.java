package tashariko.com.writetoscreenapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

    public CustomDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        DEFAULT_COLOR = Color.BLACK; //ContextCompat.getColor(getContext(), R.color.colorAccent);
        currentColor = Color.BLACK;
        initPaintNPen(currentColor);
        finalPath = new Path();
        intendedPath = new Path();
        intendedPath.addCircle(500, 500, 400, Path.Direction.CW);

        intendedPaint = new Paint();
        intendedPaint.setColor(Color.RED);
        intendedPaint.setStyle(Paint.Style.STROKE);
        intendedPaint.setStrokeWidth(20);
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
    public boolean onTouchEvent( MotionEvent event) {
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
        for (int i = 0; i < paintPenList.size(); i++) {
            canvas.drawPath(pathPenList.get(i), paintPenList.get(i));
            finalPath.addPath(pathPenList.get(i));
        }
        canvas.drawPath(intendedPath, intendedPaint);
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

    public void resetView() {
        currentColor = DEFAULT_COLOR;
        state = STATE_STILL;

        latestPath.reset();
        latestPaint.reset();

        pathPenList.clear();
        paintPenList.clear();

        finalPath.reset();
        lineWidth = 20;

        initPaintNPen(Color.BLACK);

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
        Path intersection = new Path();
        Path union = new Path();
        /*
        intersection.op(finalPath, intendedPath, Path.Op.INTERSECT);
        union.op(finalPath, intendedPath, Path.Op.UNION);
        float[] intersection_approx = intersection.approximate((float) 0.1);
        float[] union_approx = union.approximate((float) 0.1);*/
        return (float) 100.01;
    }

    public interface GetCoordinateCallback {
        void moving(float x, float y);
        void start(float x, float y);
        void end(float x, float y);
    }
}

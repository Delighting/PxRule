package com.ll.pxrule;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by ll on 18-7-24.
 */

public class PxRule extends View {

    public static int ORIENTATION_HORIZONTAL = 0;
    public static int ORIENTATION_VERTICAL = 1;

    private int mStart = 0;
    private int mEnd = 1920;
    private boolean mHorizontal;

    private Paint mBackgroundPaint;
    private Paint mLinePaint;
    private Paint mTextPaint;

    private int moveX = 0;
    private int moveY = 0;

    public PxRule(Context context, int start, int end, int orientation) {
        super(context);
        this.mStart = start;
        this.mEnd = end;
        this.mHorizontal = orientation == ORIENTATION_HORIZONTAL;

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(Color.CYAN);
        mBackgroundPaint.setAlpha(100);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.BLUE);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(20);
        mTextPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.i("liulei","on draw");
        canvas.save();
        canvas.drawPaint(mBackgroundPaint);
        for (int i = mStart; i < mEnd; i++) {
            if (i % 10 == 0) {
                //起点x坐标10像素，画厘米线
                canvas.drawLine(0, 0, mHorizontal ? 0 : 72, mHorizontal ? 72 : 0, mLinePaint);

                String text = i * 10 + "";
                Rect rect = new Rect();
                float txtWidth = mTextPaint.measureText(text);
                mTextPaint.getTextBounds(text, 0, text.length(), rect);

                if (mHorizontal) {
                    canvas.drawText(text, 0 - txtWidth / 2, 72 + rect.height() + 5, mTextPaint);
                } else {
                    canvas.drawText(text, 72 + 5, rect.height() / 2, mTextPaint);
                }


            } else if (i % 5 == 0) {
                //每隔0.5cm画间隔线
                canvas.drawLine(0, 0, mHorizontal ? 0 : 64, mHorizontal ? 64 : 0, mLinePaint);
            } else {
                //画毫米线
                canvas.drawLine(0, 0, mHorizontal ? 0 : 48, mHorizontal ? 48 : 0, mLinePaint);
            }
            //每隔10像素移动一次，达到画线效果
            canvas.translate(mHorizontal ? 10 : 0, mHorizontal ? 0 : 10);
        }
        canvas.restore();
    }

    public boolean isHorizontal() {
        return mHorizontal;
    }
}

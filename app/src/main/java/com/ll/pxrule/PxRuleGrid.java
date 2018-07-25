package com.ll.pxrule;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by ll on 18-7-24.
 */

public class PxRuleGrid extends FrameLayout {

    private PxRule mHorizontal;
    private PxRule mVertical;

    ViewDragHelper helper;

    public PxRuleGrid(Context context, int width, int height) {
        super(context);
        LayoutParams lph = new LayoutParams(LayoutParams.MATCH_PARENT, 130);
        LayoutParams lpv = new LayoutParams(130, LayoutParams.MATCH_PARENT);
        lph.topMargin = height / 3;
        lpv.leftMargin = width / 3;
        mHorizontal = new PxRule(context, 0, width, PxRule.ORIENTATION_HORIZONTAL);
        mVertical = new PxRule(context, 0, height, PxRule.ORIENTATION_VERTICAL);
        addView(mHorizontal, lph);
        addView(mVertical, lpv);

        helper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                Log.i("liulei", "child=" + child + " left=" + left + "dx=" + dx);
                PxRule rule = (PxRule) child;
                if (rule.isHorizontal()) {
                    return 0;
                }
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
//                Log.i("liulei", "child=" + child + " top=" + top + "dy=" + dy);
                PxRule rule = (PxRule) child;
                if (!rule.isHorizontal()) {
                    return 0;
                }
                return top;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        helper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        helper.shouldInterceptTouchEvent(ev);
        return true;
    }
}

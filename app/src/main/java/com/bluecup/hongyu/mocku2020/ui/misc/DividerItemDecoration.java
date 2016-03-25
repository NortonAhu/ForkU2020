package com.bluecup.hongyu.mocku2020.ui.misc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_下午6:56
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    public static final int VERTICAL_LIST = LinearLayout.VERTICAL;
    public static final int HORIZONTAL_LIST = LinearLayout.HORIZONTAL;
    private final boolean rtl;
    private final int[] attrs = {android.R.attr.divider};
    private final Drawable divider;
    private int orientation;
    private float paddingStart;


    public DividerItemDecoration(Context context, int orientation, float paddingStart, boolean rtl) {
        this.rtl = rtl;

        final TypedArray a = context.obtainStyledAttributes(attrs);

        divider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);

        setPaddingStart(paddingStart);

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (orientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin +
                    Math.round(ViewCompat.getTranslationX(child));
            final int right = left + divider.getIntrinsicHeight();
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = (int) (parent.getPaddingLeft() + (rtl ? 0 : paddingStart));
        final int right = (int) (parent.getWidth() - parent.getPaddingRight() + (rtl ? paddingStart : 0));

        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            final View childView = parent.getChildAt(i);
            final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int top = childView.getBottom() + layoutParams.bottomMargin
                    + Math.round(ViewCompat.getTranslationY(childView));
            final int bottom = top + divider.getIntrinsicHeight();
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

    private void setPaddingStart(float paddingStart) {
        if (paddingStart < 0) {
            throw new IllegalArgumentException("paddingStart < 0");
        }
        this.paddingStart = paddingStart;
    }

    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalide orientation");
        }
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (orientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, divider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, divider.getIntrinsicWidth(), 0);
        }
    }
}

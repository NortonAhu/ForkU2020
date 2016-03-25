package com.bluecup.hongyu.mocku2020.ui.misc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewAnimator;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_下午6:18
 */
public class BetterViewAnimator extends ViewAnimator {

    public BetterViewAnimator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDisplayedChildId(int id) {
        if (getDisplayedChildId() == id) return;
        for (int i = 0, count = getChildCount(); i < count; i++) {
            if (getChildAt(i).getId() == id) {
                setDisplayedChild(id);
                return;
            }
        }
        String name = getResources().getResourceEntryName(id);
        throw  new IllegalStateException("No view with ID " + name);
    }

    public int getDisplayedChildId() {
        return getChildAt(getDisplayedChild()).getId();
    }

}

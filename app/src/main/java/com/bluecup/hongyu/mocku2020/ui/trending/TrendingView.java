package com.bluecup.hongyu.mocku2020.ui.trending;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bluecup.hongyu.mocku2020.data.Injector;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午4:16
 */
public class TrendingView extends LinearLayout implements SwipeRefreshLayout.OnRefreshListener {
    public TrendingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            Injector.obtain(context).inject(this);
        }
    }

    @Override
    public void onRefresh() {

    }
}

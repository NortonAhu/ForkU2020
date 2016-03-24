package com.bluecup.hongyu.mocku2020.ui.trending;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bluecup.hongyu.mocku2020.R;
import com.bluecup.hongyu.mocku2020.data.Injector;

import rx.subjects.PublishSubject;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午4:16
 */
public class TrendingView extends LinearLayout implements SwipeRefreshLayout.OnRefreshListener {
    private final PublishSubject<TrendingTimeSpan> timeSpanSubject;
    private final TrendingTimeSpanAdapter timeSpanAdapter;

    public TrendingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            Injector.obtain(context).inject(this);
        }
        timeSpanSubject = PublishSubject.create();
        timeSpanAdapter = new TrendingTimeSpanAdapter(new ContextThemeWrapper(getContext(), R.style.Theme_U2020_TrendingTimespan));
    }

    @Override
    public void onRefresh() {

    }
}

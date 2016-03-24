package com.bluecup.hongyu.mocku2020.ui.trending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluecup.hongyu.mocku2020.R;
import com.bluecup.hongyu.mocku2020.ui.misc.EnumAdapter;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_下午3:26
 */
public final class TrendingTimeSpanAdapter extends EnumAdapter<TrendingTimeSpan>{
    public TrendingTimeSpanAdapter(Context context) {
        super(context, TrendingTimeSpan.class);
    }

    @Override
    protected View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return layoutInflate.inflate(R.layout.trending_time_span_view, container, false);
    }
}

package com.bluecup.hongyu.mocku2020.ui.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_下午2:53
 */
abstract class BindableAdapter<T> extends BaseAdapter {

    public final Context mContext;
    public final LayoutInflater layoutInflate;

    public BindableAdapter(Context context) {
        this.mContext = context;
        this.layoutInflate = LayoutInflater.from(context);

    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public abstract T getItem(int position);

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = newView(layoutInflate, position, parent);
            if (convertView == null) {
                throw  new IllegalStateException("new view result must not be null");
            }
        }
        bindView(getItem(position), position, convertView);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = newDropView(layoutInflate, position, parent);
            if (convertView == null) {
                throw  new IllegalStateException("new view result must not be null");
            }
        }
        newDropBindView(getItem(position), position, convertView);
        return convertView;
    }

    private void newDropBindView(T item, int position, View convertView) {
        bindView(item, position, convertView);
    }

    private View newDropView(LayoutInflater layoutInflate, int position, ViewGroup parent) {
        return newView(layoutInflate, position, parent);
    }


    protected abstract void bindView(T item, int position , View view);

    protected abstract View newView(LayoutInflater inflater, int position, ViewGroup container);
}

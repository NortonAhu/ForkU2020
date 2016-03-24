package com.bluecup.hongyu.mocku2020.ui.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_下午3:08
 */
public class EnumAdapter<T extends Enum<T>> extends BindableAdapter<T> {
    private final T[] enumConstants;
    private final boolean showNull;
    private final int nullOffset;

    public EnumAdapter(Context context, Class<T> enumType) {
        this(context, enumType, false);
    }

    public EnumAdapter(Context context, Class<T> enumType, boolean showNull) {
        super(context);
        this.enumConstants = enumType.getEnumConstants();
        this.showNull = showNull;
        this.nullOffset = showNull ? 1 : 0;
    }

    @Override
    public T getItem(int position) {
        if (showNull && position == 0) return null;
        return enumConstants[position - nullOffset];
    }

    @Override
    protected void bindView(T item, int position, View view) {
        TextView textView = ButterKnife.findById(view, android.R.id.text1);
        textView.setText(getName(item));
    }

    private String getName(T item) {
        return String.valueOf(item);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    protected View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return layoutInflate.inflate(android.R.layout.simple_spinner_item, container , false);
    }


    @Override
    public int getCount() {
        return enumConstants.length + nullOffset;
    }
}

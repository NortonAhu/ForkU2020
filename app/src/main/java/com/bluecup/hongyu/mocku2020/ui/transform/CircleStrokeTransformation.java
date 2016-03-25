package com.bluecup.hongyu.mocku2020.ui.transform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_下午4:13
 */
public class CircleStrokeTransformation implements Transformation {

    private final int strokeColor;
    private final Paint strokePaint;
    private final int strokeWidth;

    public CircleStrokeTransformation(Context context, int strokeColor, int strokeWidthDp) {
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidthDp * context.getResources().getDisplayMetrics().densityDpi;
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setStyle(Paint.Style.FILL);
        strokePaint.setColor(strokeColor);
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int size = source.getWidth();
        Bitmap rounded = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(rounded);
        BitmapShader shader = new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint shadePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shadePaint.setShader(shader);

        RectF rectF = new RectF(0, 0, size, size);

        float radius = size/2;
        canvas.drawRoundRect(rectF, radius, radius, shadePaint);
        strokePaint.setStrokeWidth(strokeWidth);

        float strokeInset = strokeWidth / 2f;
        rectF.inset(strokeInset, strokeInset);

        float strokeRadius = radius - strokeInset;

        canvas.drawRoundRect(rectF, strokeRadius, strokeRadius, strokePaint);
        source.recycle();
        return rounded;
    }

    @Override
    public String key() {
        return "circle_stroke(" + strokeColor + "," + strokeWidth + ")";
    }
}

package com.example.administrator.summarize.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * Created by zzk on 2016/7/20.
 */
public class RoundImage extends android.support.v7.widget.AppCompatImageView {

    private static final int DEFAULT_STROKE_WIDTH = 0;

    private int mRadius;
    private int mStrokeWidth = DEFAULT_STROKE_WIDTH;
    private Paint mPaint;

    private Bitmap mResource;
    private Xfermode mXfermode;

    private int mCenter;
    private int mWidth;

    public RoundImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setBitmap(Bitmap bitmap) {
        this.mResource = bitmap;
        invalidate();
    }

    private void init() {
        mPaint = new Paint();
//        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mStrokeWidth);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

        setBackgroundDrawable(null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());

        mCenter = (mWidth - mStrokeWidth) / 2;
        mRadius = mCenter - mStrokeWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (mResource != null) {
            canvas.drawBitmap(getRoundBitmap(mResource), 0, 0, mPaint);
        } else {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                canvas.drawBitmap(getRoundBitmap(getBackgroundBitmap(drawable)), 0, 0, mPaint);
            }
        }
//
////        mPaint.setColor(Color.WHITE);
//        //描边
//        mPaint.setStyle(Paint.Style.STROKE);
//        //不描边
////        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStrokeWidth(mStrokeWidth);
//        canvas.drawCircle(mCenter, mCenter, mRadius, mPaint);
    }

    //将两张图片以XferMode（DST_IN）的方式组合到一张照片中
    private Bitmap getRoundBitmap(Bitmap resource) {
        if (resource == null)
            return null;

        int bmpWidth = resource.getWidth();
        int bmpHeight = resource.getHeight();

        // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
        float scale = Math.max(getWidth() * 1.0f / bmpWidth, getHeight() * 1.0f / bmpHeight);
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        Bitmap bitmap = Bitmap.createBitmap(resource, 0, 0, bmpWidth, bmpHeight, matrix, true);
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Rect rect = new Rect(0, 0, mWidth, mWidth);
        mPaint.reset();


        mPaint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(mCenter, mCenter, mRadius, mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(bitmap, rect, rect, mPaint);
        mPaint.setXfermode(null);

        return output;
    }


    private Bitmap getBackgroundBitmap(Drawable drawable) {
        int dWidth = drawable.getIntrinsicWidth();
        int dHeight = drawable.getIntrinsicHeight();

        float scale = Math.max(getWidth() * 1.0f / dWidth, getHeight() * 1.0f / dHeight);
        Bitmap output = Bitmap.createBitmap(getWidth(),
                getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        drawable.setBounds(0, 0, (int) (scale * dWidth),
                (int) (scale * dHeight));
        drawable.draw(canvas);
        return output;
    }

}

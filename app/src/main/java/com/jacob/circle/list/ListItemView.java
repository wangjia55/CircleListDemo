package com.jacob.circle.list;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jacob-wj on 2015/4/7.
 */
public class ListItemView extends LinearLayout {
    private ImageView mImageView;
    private TextView mTextView;
    private int mParentHeight;
    private int mHalfHeight;
    private float fullAngelFactor = 20f;
    private float fullScaleFactor = 1;
    private float fullTranslateXFactor = 40;
    private float fullTranslateYFactor = 35;

    public ListItemView(Context context) {
        this(context, null);
    }

    public ListItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_list_item, this);
        mImageView = (ImageView) findViewById(R.id.image_view);
        mTextView = (TextView) findViewById(R.id.text_view);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        int top = getTop();
        float rotate = calculateAngel(top);
        float scale = calculateScale(top);
        float translateX = calculateTranslate(top);
        float translateY = (1-scale)*fullTranslateYFactor;

        Matrix matrix = canvas.getMatrix();
        matrix.postScale(scale, scale);
        matrix.postTranslate(translateX,translateY);
        matrix.postRotate(rotate);
        canvas.concat(matrix);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public void setAppBean(AppBean appBean) {
        mImageView.setImageResource(appBean.getAvatar());
        mTextView.setText(appBean.getName());
    }

    public void setParentHeight(int height) {
        mParentHeight = height;
        mHalfHeight = (int) (height / 2f);
    }

    private float calculateAngel(int top) {
        float result = 0f;
        if (top < mHalfHeight) {
            result = (top - mHalfHeight) * 1.0f / mHalfHeight * fullAngelFactor;
        } else if (top > mHalfHeight) {
            result = (top - mHalfHeight) * 1.0f / mHalfHeight * fullAngelFactor;
        }
        Log.e("TAG", result + "");
        return result;
    }

    private float calculateScale(int top) {
        float result = 0f;
        result = (1f - 1f / 2f * Math.abs((top - mHalfHeight)) / mHalfHeight) * fullScaleFactor;
        return result;
    }

    private float calculateTranslate(int top) {
        float result = 0f;
        result = (1f - 1f / 2f * Math.abs((top - mHalfHeight)) / mHalfHeight) * fullTranslateXFactor;
        return result;
    }

}

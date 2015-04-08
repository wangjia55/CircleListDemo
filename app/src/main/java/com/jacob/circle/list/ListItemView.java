package com.jacob.circle.list;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jacob-wj on 2015/4/7.
 */
public class ListItemView extends LinearLayout {
    private ImageView mImageView;
    private TextView mTextView;
    /**
     * listView的一半的高度，这个要根据高度作为分割线
     */
    private int mHalfHeight;

    /**
     * 最大的旋转角度
     */
    private float fullAngelFactor = 20f;
    /**
     * 最大的缩放
     */
    private float fullScaleFactor = 1;
    /**
     * X轴最大的偏移量
     */
    private float fullTranslateXFactor = 40;
    /**
     * Y轴最大的偏移量
     */
    private float fullTranslateYFactor = 35;

    public ListItemView(Context context) {
        this(context, null);
    }

    public ListItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        LayoutInflater.from(context).inflate(R.layout.layout_list_item, this);
        mImageView = (ImageView) findViewById(R.id.image_view);
        mTextView = (TextView) findViewById(R.id.text_view);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        int top = getTop();
        float rotate = calculateAngel(top);
        float scale = calculateScale(top);
        float translateX = calculateTranslateX(top);
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
        mImageView = (ImageView) findViewById(R.id.image_view);
        mTextView = (TextView) findViewById(R.id.text_view);
        mImageView.setImageResource(appBean.getAvatar());
        mTextView.setText(appBean.getName());
    }

    /**
     * 传入listView的高度，并且计算一半的高度
     */
    public void setParentHeight(int height) {
        mHalfHeight = (int) (height / 2f);
    }

    /**
     * 计算角度，根据top和halfHeight的比值
     */
    private float calculateAngel(int top) {
        float result = 0f;
        if (top < mHalfHeight) {
            result = (top - mHalfHeight) * 1.0f / mHalfHeight * fullAngelFactor;
        } else if (top > mHalfHeight) {
            result = (top - mHalfHeight) * 1.0f / mHalfHeight * fullAngelFactor;
        }
        return result;
    }

    /**
     * 计算缩放的比例
     */
    private float calculateScale(int top) {
        float result = 0f;
        result = (1f - 1f / 2f * Math.abs((top - mHalfHeight)) / mHalfHeight) * fullScaleFactor;
        return result;
    }

    /**
     * 计算X偏移的位置
     */
    private float calculateTranslateX(int top) {
        float result = 0f;
        result = (1f - 1f / 2f * Math.abs((top - mHalfHeight)) / mHalfHeight) * fullTranslateXFactor;
        return result;
    }

}

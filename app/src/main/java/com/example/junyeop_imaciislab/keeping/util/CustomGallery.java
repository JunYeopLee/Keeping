package com.example.junyeop_imaciislab.keeping.util;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

public class CustomGallery extends Gallery {

    private static Camera mCamera;

    public CustomGallery(Context context) {
        this(context, null);
    }

    public CustomGallery(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mCamera = new Camera();
        setSpacing(-40);
    }

    protected boolean getChildStaticTransformation(View child, Transformation t) {
        final int mCenter = (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
        final int childCenter = child.getLeft() + child.getWidth() / 2;
        final int childWidth = child.getWidth();

        t.clear();
        t.setTransformationType(Transformation.TYPE_MATRIX);
        float rate = Math.abs((float) (mCenter - childCenter) / childWidth);

        mCamera.save();
        final Matrix matrix = t.getMatrix();
        float zoomAmount = (float) (rate * 200.0);
        mCamera.translate(0.0f, 0.0f, zoomAmount);
        mCamera.getMatrix(matrix);
        matrix.preTranslate(-(childWidth / 2), -(childWidth / 2));
        matrix.postTranslate((childWidth / 2), (childWidth / 2));
        mCamera.restore();

        return true;
    }

}
package com.banketree.pk10game.components;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.banketree.pk10game.R;


/**
 * 默认头部bar
 */

public class TitleBar extends RelativeLayout {
    protected View rootView;
    protected ImageView leftImageView;
    protected ImageView rightImageView;
    protected TextView titleTextView;
    protected TextView leftTextView;
    protected TextView rightTextView;
    protected View lineView;
    protected Toolbar toolbar;

    public final static int STYLE_WRITE = 0;
    public final static int STYLE_TRANSPARENT = 1;

    private Context context;

    public TitleBar(Context context) {
        super(context);
        this.context = context;
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init(context, attrs);
    }

    @SuppressLint("WrongConstant")
    private void init(Context context, AttributeSet attrs) {
        rootView = LayoutInflater.from(context).inflate(R.layout.layout_bar_com, this, true);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        leftImageView = rootView.findViewById(R.id.img_left);
        rightImageView = rootView.findViewById(R.id.img_right);
        titleTextView = rootView.findViewById(R.id.txt_name);
        lineView = rootView.findViewById(R.id.view_line);
        rightTextView = rootView.findViewById(R.id.tv_right);
        leftTextView = rootView.findViewById(R.id.tv_left);

        toolbar.setBackgroundColor(Color.WHITE);

        TypedArray types = context.obtainStyledAttributes(attrs, R.styleable.titlebar_layout);
        final int count = types.getIndexCount();
        for (int i = 0; i < count; i++) {
            try {
                int attr = types.getIndex(i);
                if (attr == R.styleable.titlebar_layout_titleText) {
                    String titleText = types.getString(R.styleable.titlebar_layout_titleText);
                    titleTextView.setText(titleText);
                } else if (attr == R.styleable.titlebar_layout_titleTextColor) {
                    int titleTextColor = types.getColor(attr, context.getResources().getColor(R.color.toolbar_front));
                    titleTextView.setTextColor(titleTextColor);
                } else if (attr == R.styleable.titlebar_layout_leftText) {
                    String leftText = types.getString(R.styleable.titlebar_layout_leftText);
                    leftTextView.setVisibility(VISIBLE);
                    leftImageView.setVisibility(GONE);
                    leftTextView.setText(leftText);
                } else if (attr == R.styleable.titlebar_layout_leftTextColor) {
                    int leftTextColor = types.getColor(attr, Color.WHITE);
                    leftTextView.setTextColor(leftTextColor);
                } else if (attr == R.styleable.titlebar_layout_leftImg) {
                    Drawable leftImg = types.getDrawable(R.styleable.titlebar_layout_leftImg);
                    leftImageView.setImageDrawable(leftImg);
                    leftImageView.setVisibility(VISIBLE);
                    leftTextView.setVisibility(GONE);
                } else if (attr == R.styleable.titlebar_layout_rightText) {
                    String rightText = types.getString(R.styleable.titlebar_layout_rightText);
                    rightTextView.setText(rightText);
                    rightTextView.setVisibility(VISIBLE);
                    rightImageView.setVisibility(GONE);
                } else if (attr == R.styleable.titlebar_layout_rightTextColor) {
                    int rightTextColor = types.getColor(attr, Color.WHITE);
                    rightTextView.setTextColor(rightTextColor);
                } else if (attr == R.styleable.titlebar_layout_rightImg) {
                    Drawable rightImg = types.getDrawable(R.styleable.titlebar_layout_rightImg);
                    rightImageView.setImageDrawable(rightImg);
                    rightTextView.setVisibility(GONE);
                    rightImageView.setVisibility(VISIBLE);
                } else if (attr == R.styleable.titlebar_layout_leftImgColor) {
                    int leftImgColor = types.getColor(attr, 0);
//                    leftImageView.setColorFilter(leftImgColor);
                } else if (attr == R.styleable.titlebar_layout_rightImgColor) {
                    int rightImgColor = types.getColor(attr, 0);
                    rightImageView.setColorFilter(rightImgColor);
                } else if (attr == R.styleable.titlebar_layout_showLine) {
                    boolean showLine = types.getBoolean(attr, false);
                    lineView.setVisibility(showLine ? VISIBLE : INVISIBLE);
                } else if (attr == R.styleable.titlebar_layout_backgroundColor) {
                    int bgColor = types.getColor(attr, Color.WHITE);
                    toolbar.setBackgroundColor(bgColor);
                } else if (attr == R.styleable.titlebar_layout_showLineBg) {
                    int bgColor = types.getColor(attr, context.getResources().getColor(R.color.color_divide_line));
                    lineView.setBackgroundColor(bgColor);
                }
            } catch (Exception e) {
            }
        }


        types.recycle();
    }


    public ImageView getLeftImageView() {
        return leftImageView;
    }

    public TextView getLeftTextView() {
        return leftTextView;
    }




    public ImageView getRightImageView() {
        return rightImageView;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public TextView getRightTextView() {
        return rightTextView;
    }

    public View getLineView() {
        return lineView;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        if (leftImageView != null) {
            leftImageView.setOnClickListener(onClickListener);
        }

        if (rightImageView != null) {
            rightImageView.setOnClickListener(onClickListener);
        }

        if (titleTextView != null) {
            titleTextView.setOnClickListener(onClickListener);
        }

        if (rightTextView != null) {
            rightTextView.setOnClickListener(onClickListener);
        }

        if (leftTextView != null) {
            leftTextView.setOnClickListener(onClickListener);
        }
    }

    //(x,y)是否在view的区域内
    public static boolean isTouchPointInView(View view, float x, float y) {
        if (view == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        //view.isClickable() &&
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }
}

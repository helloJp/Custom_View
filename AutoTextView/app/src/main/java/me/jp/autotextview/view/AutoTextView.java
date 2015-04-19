package me.jp.autotextview.view;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * TextView的最后一行，不完全显示文本。
 * Created by jiangp on 15/4/19.
 */
public class AutoTextView extends TextView {

    private int mEmptyWidth = 200;//空白文本宽度
    private int mMinLine = 2;

    public AutoTextView(Context context) {
        this(context, null);
    }

    public AutoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mEmptyWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mEmptyWidth, context.getResources().getDisplayMetrics());
    }


    public void setAutoText(final CharSequence text) {
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                //可显示文本区域的宽度
                int availableTextWidth = mMinLine * (getWidth() - getPaddingLeft() - getPaddingRight()) - mEmptyWidth;
                Paint paint = getPaint();
                paint.setTextSize(getTextSize());

                // 根据长度截取出剪裁后的文字
                String ellipsizeStr = (String) TextUtils.ellipsize(text, (TextPaint) paint, availableTextWidth, TextUtils.TruncateAt.END);
                setText(ellipsizeStr);
            }
        });

    }


}

package me.jp.slidingmenu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.display.DisplayManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import me.jp.slidingmenu.R;

/**
 * Created by JiangPing on 2014/11/21.
 * 自定义ViewGroup
 * 1、onMeasure
 * 决定内部View(子View的宽和高、以及自己的宽和高)
 * 2、onLayout
 * 决定子View放置位置
 * 3、onTouchEvent
 * 根据Action决定内部View的移动效果
 * <p/>
 * <p/>
 * 自定义属性，设置LeftMenu距离右屏幕的距离
 * 1、书写XML文件 values/attr.xml
 * 2、在布局文件中进行使用，特别注意xmlns
 * 3、在构造方法中（3个参数的构造方法）中获取自定义属性
 */
public class SlidingMenu extends HorizontalScrollView {
    private final String TAG = SlidingMenu.class.getName();

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;

    private int mScreenWidth;
    private int mMenuWidth;
    /**
     * menu距离屏幕右边的距离
     * 单位dp
     */
    private int mMenuRightPadding;

    private boolean once;//onMeasure是否被调用

    private boolean isOpen;

    public SlidingMenu(Context context) {
        this(context, null);
    }

    /**
     * 未使用自定属性的时调用，
     *
     * @param context
     * @param attrs
     */
    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 当使用了自定义属性时，会调用此构造方法
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            Log.i(TAG, "attr:" + attr);
            switch (attr) {
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightPadding = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMenuRightPadding, context.getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;//设置屏幕宽度

    }

    /**
     * 设置子View的宽和高，设置自己的宽和高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!once) {
            mWapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);

            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;
            once = true;
        }
    }

    /**
     * 通过设置偏移量,将menu隐藏
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(mMenuWidth, 0);//将Menu隐藏到左侧
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                //隐藏在左边的宽度
                int scrollX = getScrollX();
                if (scrollX > mMenuWidth / 2) {
                    //隐藏宽度大于/2,滑动Menu小于一半，隐藏Menu
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                } else {
                    //隐藏宽度小于menu/2,滑动Menu超出一半，显示menu
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 打开菜单
     */
    public void openMenu() {
        if (isOpen) return;
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (!isOpen) return;
        this.smoothScrollTo(mMenuWidth, 0);
        isOpen = false;
    }

    /**
     * 切换菜单
     */
    public void toggle() {
        if (isOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }
    
}

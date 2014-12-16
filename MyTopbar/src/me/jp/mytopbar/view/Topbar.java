package me.jp.mytopbar.view;

import me.jp.mytopbar.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Topbar extends RelativeLayout {

	/**
	 * 按钮模式 -1表示没有button ，0表示有两个button， 1表示只有leftButton，2表示只有rightButton
	 */
	private int buttonMode;

	private Button leftButton, rightButton;
	private TextView tvTitle;

	private float leftMargin;// leftButton Margin left
	private int leftTextColor;
	private float leftTextSize;
	private Drawable leftBackground;
	private String leftText;
	private float leftWidth;
	private float leftHeight;

	private float rightMargin;// rightButton Margin right
	private int rightTextColor;
	private float rightTextSize;
	private Drawable rightBackground;
	private String rightText;
	private float rightWidth;
	private float rightHeight;

	private float titleTextSize;
	private int titleTextColor;
	private String title;

	private LayoutParams leftParams, rightParams, titleParams;

	private Drawable background;

	private TopbarLeftClickListener leftListener;
	private TopbarRightClickListener rightListener;

	public interface TopbarLeftClickListener {
		public void leftClick();
	}

	public interface TopbarRightClickListener {
		public void rightClick();
	}

	public void setOnTopbarLeftClickListener(TopbarLeftClickListener listener) {
		this.leftListener = listener;
	}

	public void setOnTopbarRightClickListener(TopbarRightClickListener listener) {
		this.rightListener = listener;
	}

	public Topbar(Context context) {
		this(context, null);
	}

	@SuppressWarnings("deprecation")
	public Topbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.Topbar);

		titleTextSize = ta.getDimension(R.styleable.Topbar_titleTextSize, 0);
		Log.i("TestData", "titleTextSize:" + titleTextSize);
		titleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor, 0);
		title = ta.getString(R.styleable.Topbar_title);

		buttonMode = ta.getInteger(R.styleable.Topbar_buttonMode, -1);
		background = ta.getDrawable(R.styleable.Topbar_background);

		switch (buttonMode) {
		case 0:// 0表示有两个button
			initLeftButton(context, ta);
			initRightButton(context, ta);
			break;
		case 1:// 1表示只有leftButton
			initLeftButton(context, ta);
			break;
		case 2:// 2表示只有rightButton
			initRightButton(context, ta);
			break;
		default:// 表示没有button
			break;
		}

		ta.recycle();

		setBackgroundDrawable(background);// 设置Topbar背景
		tvTitle = new TextView(context);

		tvTitle.setTextSize(titleTextSize);
		tvTitle.setTextColor(titleTextColor);
		tvTitle.setText(title);
		tvTitle.setGravity(Gravity.CENTER);

		titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.MATCH_PARENT);
		titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
		addView(tvTitle, titleParams);
	}

	@SuppressWarnings("deprecation")
	private void initRightButton(Context context, TypedArray ta) {
		rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);
		rightTextSize = ta.getDimension(R.styleable.Topbar_rightTextSize, 0);
		rightBackground = ta.getDrawable(R.styleable.Topbar_rightBackground);
		rightText = ta.getString(R.styleable.Topbar_rightText);
		rightWidth = ta.getDimension(R.styleable.Topbar_rightWidth, 0);
		rightHeight = ta.getDimension(R.styleable.Topbar_rightHeight, 0);
		rightMargin = ta.getDimension(R.styleable.Topbar_rightMargin, 0);

		rightButton = new Button(context);

		rightButton.setTextColor(rightTextColor);
		rightButton.setBackgroundDrawable(rightBackground);
		rightButton.setText(rightText);
		if (0 != rightTextSize)
			rightButton.setTextSize(rightTextSize);

		rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
		rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
		if (0 != rightWidth) {
			rightParams.width = (int) rightWidth;
		}
		if (0 != rightHeight) {
			rightParams.height = (int) rightHeight;
		}
		if (0 != rightMargin) {
			rightParams.setMargins(0, 0, (int) rightMargin, 0);
		}

		addView(rightButton, rightParams);

		rightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				rightListener.rightClick();
			}
		});

	}

	@SuppressWarnings("deprecation")
	private void initLeftButton(Context context, TypedArray ta) {
		leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);
		leftTextSize = ta.getDimension(R.styleable.Topbar_leftTextSize, 0);
		leftBackground = ta.getDrawable(R.styleable.Topbar_leftBackground);
		leftText = ta.getString(R.styleable.Topbar_leftText);
		leftWidth = ta.getDimension(R.styleable.Topbar_leftWidth, 0);
		leftHeight = ta.getDimension(R.styleable.Topbar_leftHeight, 0);
		leftMargin = ta.getDimension(R.styleable.Topbar_leftMargin, 0);

		leftButton = new Button(context);

		leftButton.setTextColor(leftTextColor);
		leftButton.setBackgroundDrawable(leftBackground);
		leftButton.setText(leftText);
		if (0 != leftTextSize)
			leftButton.setTextSize(leftTextSize);

		leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
		leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
		if (0 != leftWidth) {
			leftParams.width = (int) leftWidth;
		}
		if (0 != leftHeight) {
			leftParams.height = (int) leftHeight;
		}
		if (0 != leftMargin) {
			leftParams.setMargins((int) leftMargin, 0, 0, 0);
		}

		addView(leftButton, leftParams);

		leftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				leftListener.leftClick();
			}
		});
	}

}

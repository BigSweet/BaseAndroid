package cn.primedu.m.baselib.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import cn.primedu.m.baselib.R;
import cn.primedu.m.baselib.util.DisplayUtils;


public class PageControlView extends LinearLayout {

	private int mPages = 3;

	private Bitmap mWhiteDot;
	private Bitmap mRedDot;

	private int mCurIndex = 0;
	private int mDotOffset = DisplayUtils.dip2px(8);

	private Paint mPaint = new Paint();

	public PageControlView(Context context) {
		super(context);
		this.initView();
	}

	public PageControlView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initView();
	}

	public PageControlView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.initView();
	}

	private void initView() {

		mWhiteDot = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.page_control_inactive);
		mRedDot = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.page_control_active);

	}

	public void setCurPage(int curPage) {

		if (mCurIndex != curPage) {

			mCurIndex = curPage;
			this.invalidate();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {

		for (int i = 0; i < mPages; i++) {
			if (mCurIndex == i) {
				canvas.drawBitmap(mRedDot, getPaintX(i), getPaintY(i), mPaint);
			} else {
				canvas.drawBitmap(mWhiteDot, getPaintX(i), getPaintY(i), mPaint);
			}
		}
	}

	private float getPaintX(int index) {
		int width = this.getWidth();

		int dotsWidth = mWhiteDot.getWidth() * mPages + (mPages - 1)
				* mDotOffset + (mRedDot.getWidth() - mWhiteDot.getWidth());
		int startX = (width - dotsWidth) / 2;

		if (index <= mCurIndex) {
			return startX + index * (mWhiteDot.getWidth() + mDotOffset);
		} else {
			return startX + index * (mWhiteDot.getWidth() + mDotOffset)
					+ (mRedDot.getWidth() - mWhiteDot.getWidth());
		}
	}

	private float getPaintY(int index) {
		int height = this.getHeight();
		if (index == mCurIndex) {
			return (height - mRedDot.getHeight()) / 2;
		} else {
			return (height - mWhiteDot.getHeight()) / 2;
		}
	}

	public void setPages(int pages) {
		this.mPages = pages;
		this.invalidate();
	}

}

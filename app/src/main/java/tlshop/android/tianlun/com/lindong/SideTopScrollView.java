package tlshop.android.tianlun.com.lindong;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class SideTopScrollView extends ScrollView {

	private OnChildScrollListener mAction;

	private boolean isScrollTop = false;

	public SideTopScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean bool = mAction != null && mAction.isChildScroll() && super.onInterceptTouchEvent(ev);
		Log.i(SideGroupLayout.TAG, "ScrollView的onInterceptTouchEvent：" + bool);
		return bool;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mAction != null && mAction.isChildScroll() && super.onTouchEvent(event);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		if (t == 0) {
			isScrollTop = true;
		} else {
			isScrollTop = false;
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}

	public boolean isScrollToTop() {
		return isScrollTop;
	}

	public void setOnChildScrollListener(OnChildScrollListener action) {
		this.mAction = action;
	}


}

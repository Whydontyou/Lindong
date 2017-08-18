package tlshop.android.tianlun.com.lindong;

/**
 * Group需要实现的接口
 * 
 * @author 巍
 * 
 */
public interface OnGroupScrollListener {
	/**
	 * 询问Child是否还需要滚动
	 * 
	 * @return
	 */
	public boolean isGroupScroll();

	void onScrollChanged(int left, int top);
}

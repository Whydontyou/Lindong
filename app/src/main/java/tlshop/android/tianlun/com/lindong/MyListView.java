package tlshop.android.tianlun.com.lindong;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyListView extends ListView {  
	  
    public MyListView(Context context) {  
        super(context);
    }  
  
    public MyListView(Context context, AttributeSet attrs) {  
        super(context, attrs);
    }  
  
    public MyListView(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

  /*  public boolean dispatchTouchEvent(MotionEvent ev) {

        return false;


    }*/

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

}

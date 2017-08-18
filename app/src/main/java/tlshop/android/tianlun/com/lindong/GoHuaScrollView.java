package tlshop.android.tianlun.com.lindong;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by 莫小婷 on 2017/3/20.
 */

public class GoHuaScrollView extends ScrollView {
    public GoHuaScrollView(Context context) {
        super(context);
    }

    public GoHuaScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GoHuaScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
   /* @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("wjjj",""+ev.getAction());
        if (MotionEvent.ACTION_MOVE==(ev.getAction())){
            return true;
        }
        return  false;
    }
*/
}

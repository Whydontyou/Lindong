package tlshop.android.tianlun.com.lindong;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.os.StrictMode;

import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

/**
 * Created by 马小布 on 2016/9/2.
 */
public class App extends Application {
    public static final String APP_ID = "2882303761517525233";
    public static final String APP_KEY = "5361752551233";
    private static App instance;


    private static List<String> GroupIdList = new ArrayList<>();
    private static List<String> AvarList = new ArrayList<>();
    private static List<String> GroupIidList = new ArrayList<>();

    public static List<String> getAvarList() {
        return AvarList;
    }

    public static void setAvarList(List<String> avarList) {
        AvarList = avarList;
    }

    public static List<String> getGroupIdList() {
        return GroupIdList;
    }

    public static void setGroupIdList(List<String> groupIdList) {
        GroupIdList = groupIdList;
    }

    public static List<String> getGroupIidList() {
        return GroupIidList;
    }

    public static void setGroupIidList(List<String> groupIidList) {
        GroupIidList = groupIidList;
    }



    public static App getInstance() {
        return instance;
    }




    private Drawable mDrawableHolder;



    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;


//
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        enabledStrictMode();
//
    }

    private void enabledStrictMode() {
        if (SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll() //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
        }
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }



    public void setDrawableHolder(Drawable drawable) {
        mDrawableHolder = drawable;
    }

    public Drawable getDrawableHolder() {
        Drawable drawable = mDrawableHolder;
        mDrawableHolder = null;
        return drawable;
    }


    private long mTime;
    private Boolean flag = true;


    //误差次数
    Double max = 0.0, min = 0.0, sum = 0.0;
    private int times = 1;




}

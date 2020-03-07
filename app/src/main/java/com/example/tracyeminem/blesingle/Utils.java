package com.example.tracyeminem.blesingle;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

public class Utils {

    public static boolean isSupportBLE(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    public static void log(String context) {
        Thread thread = Thread.currentThread(); // 获取当前线程
        StackTraceElement[] trace = thread.getStackTrace(); // 获取当前线程的栈快照(入栈方法的数据)
        StringBuilder TAG = new StringBuilder();
        TAG.append("zhangwei--").append(trace[3].getFileName()).append("--").append(trace[3].getMethodName()).append(":").append(trace[3].getLineNumber());
        Log.i(TAG.toString(), context == null ? "null" : context);
    }


}

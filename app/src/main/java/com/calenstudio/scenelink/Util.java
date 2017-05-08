package com.calenstudio.scenelink;

import android.content.Context;
import android.net.Uri;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chenzq on 2017-05-05.
 */

public final class Util {
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    public static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    public  static String FormatDate(Date date,String formatStr)
    {
        // 初始化时设置 日期和时间模式
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
       return sdf.format(date);
    }

}

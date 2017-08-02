package com.fxy.permissionsdemo;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class ActivitiesController {
    //创建Activity集合
    private static List<Activity> activityList = new ArrayList<>();
    //集合添加
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }
    //集合移除
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    //获取栈顶Activity
    public static Activity getTopActivity(){
        if (activityList.isEmpty()){
            return null;
        }else {
            return activityList.get(activityList.size()-1);
        }
    }
}

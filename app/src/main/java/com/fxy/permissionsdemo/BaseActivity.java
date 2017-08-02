package com.fxy.permissionsdemo;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private static PermissionsListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ActivitiesController.addActivity(this);
    }

    public static void requestRuntimePermission(String[] permissions, PermissionsListener listener){
        Activity topActivity = ActivitiesController.getTopActivity();
        if (topActivity == null){
            return;
        }
        mListener = listener;
        List<String> pList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity,permission)!= PackageManager.PERMISSION_GRANTED){
                pList.add(permission);
            }
        }
        if (!pList.isEmpty()){
            ActivityCompat.requestPermissions(topActivity,pList.toArray(new String[pList.size()]),1);
        }
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    //定义拒绝的权限集合
                    List<String> denyPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        //方法参数中的String[] permissions
                        String permission = permissions[i];
                        if (grantResult!=PackageManager.PERMISSION_GRANTED){
                            denyPermissions.add(permission);
                        }
                    }
                    //判断拒绝权限集合是否为空
                    if (denyPermissions.isEmpty()){
                        mListener.onGranted();
                    }else {
                        mListener.onDenied(denyPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesController.removeActivity(this);
    }
}

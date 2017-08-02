package com.fxy.permissionsdemo;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public interface PermissionsListener {

    void onGranted();//同意

    void onDenied(List<String> deniedPermissions);//用户可能拒绝一个，多个，或者所有
}

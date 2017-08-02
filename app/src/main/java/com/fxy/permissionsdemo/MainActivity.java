package com.fxy.permissionsdemo;


import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends BaseActivity {
    private Button mCallButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCallButton = (Button) findViewById(R.id.call_permission);
        mCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestRuntimePermission(new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, new PermissionsListener() {
                    @Override
                    public void onGranted() {
                        Toast.makeText(MainActivity.this,"All granted!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermissions) {
                        for (String deniedPermission : deniedPermissions) {
                            Toast.makeText(MainActivity.this,"存在被拒绝的权限"+deniedPermission,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

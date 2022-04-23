package com.kdg.toast.plugin;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Arrays;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.database.sqlite.SQLiteDatabase;

import static androidx.core.app.ActivityCompat.requestPermissions;

public final class Bridge extends Application {
    static Activity myActivity;
    static Context appContext;

    public static void receiveActivityInstance(Activity tempActivity) {
        myActivity = tempActivity;
        String[] perms= new String[1];
        perms[0]=Manifest.permission.ACTIVITY_RECOGNITION;
        if (ContextCompat.checkSelfPermission(myActivity, Manifest.permission.ACTIVITY_RECOGNITION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.i("PEDOMETER", "Permision isnt granted!");
            ActivityCompat.requestPermissions(Bridge.myActivity,
                    perms,
                    1);
        }
    }

    public static int getData(){
        return 20;
    }

    public static void StartCheckerService() {
        //checkOptimization();
        //myActivity.startService(new Intent(myActivity, PedometerService.class));
    }

    public static void StopCheckerService(){
        //Intent serviceIntent = new Intent(myActivity, PedometerService.class);
        //myActivity.stopService(serviceIntent);

    }


    @Override
    public void onCreate() {
        super.onCreate();
        Bridge.appContext=getApplicationContext();
    }

    public static int getStepCountData(String startDate, String endDate){
        return 102;
    }

    public static void checkOptimization(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String packageName = myActivity.getPackageName();
            Log.i("PEDOMETER", "checkOptimization: PACKAGE NAME: "+packageName);
            PowerManager pm = (PowerManager) myActivity.getSystemService(Context.POWER_SERVICE);
            if (pm != null) {
                if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                        intent.setData(Uri.parse("package:" + packageName));
                        myActivity.startActivity(intent);
                    }
                    catch (Exception e){
                    }

                }
            }
        }
    }
}

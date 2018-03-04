package com.example.jaceriehl.clock;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.StringDef;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateClockView();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    private void updateClockView(){
        final TextView one = (TextView)findViewById(R.id._one);
        final TextView two = (TextView)findViewById(R.id._two);
        final TextView three = (TextView)findViewById(R.id._three);
        final TextView four = (TextView)findViewById(R.id._four);
        final TextView five = (TextView)findViewById(R.id._five);
        final TextView six = (TextView)findViewById(R.id._six);
        final TextView seven = (TextView)findViewById(R.id._seven);
        final TextView eight = (TextView)findViewById(R.id._eight);
        final TextView nine = (TextView)findViewById(R.id._nine);
        final TextView ten = (TextView)findViewById(R.id._ten);
        final TextView am = (TextView)findViewById(R.id._am);
        final TextView pm = (TextView)findViewById(R.id._pm);

        Calendar ctime = Calendar.getInstance();
        String hour = Integer.toString(ctime.get(Calendar.HOUR));
        String min = Integer.toString(ctime.get(Calendar.MINUTE));
        String sec = Integer.toString(ctime.get(Calendar.SECOND));

        if(Integer.parseInt(Integer.toString(ctime.get(Calendar.AM_PM))) == 1)
            pm.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        else if(Integer.toString(ctime.get(Calendar.AM_PM)) == "0")
            am.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));

        if(hour == "3")
            three.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        else if(hour == "4")
            four.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        else if(hour == "5")
            five.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
    }

}

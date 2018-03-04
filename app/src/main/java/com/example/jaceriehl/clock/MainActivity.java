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
import android.util.Log;

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
                        Thread.sleep(2000);
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

    private void updateClockView() {
        final TextView one = (TextView) findViewById(R.id._one);
        final TextView two = (TextView) findViewById(R.id._two);
        final TextView three = (TextView) findViewById(R.id._three);
        final TextView four = (TextView) findViewById(R.id._four);
        final TextView five = (TextView) findViewById(R.id._five);
        final TextView six = (TextView) findViewById(R.id._six);
        final TextView seven = (TextView) findViewById(R.id._seven);
        final TextView eight = (TextView) findViewById(R.id._eight);
        final TextView nine = (TextView) findViewById(R.id._nine);
        final TextView ten = (TextView) findViewById(R.id._ten);
        final TextView eleven = (TextView) findViewById(R.id._eleven);
        final TextView twelve = (TextView) findViewById(R.id._twelve);
        final TextView am = (TextView) findViewById(R.id._am);
        final TextView pm = (TextView) findViewById(R.id._pm);
        final TextView to = (TextView) findViewById(R.id._to);
        final TextView past = (TextView) findViewById(R.id._past);
        final TextView half = (TextView) findViewById(R.id._half);
        final TextView twentyTo = (TextView) findViewById(R.id._twentyTo);
        final TextView quarterTo = (TextView) findViewById(R.id._quarterTo);
        final TextView tenTo = (TextView) findViewById(R.id._tenTo);
        final TextView fiveTo = (TextView) findViewById(R.id._fiveTo);

        Calendar ctime = Calendar.getInstance();
        int hour = ctime.get(Calendar.HOUR);
        int min = ctime.get(Calendar.MINUTE);
        int sec = ctime.get(Calendar.SECOND);
        int amOrPm = ctime.get(Calendar.AM_PM);

        am.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        pm.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        one.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        two.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        three.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        four.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        five.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        six.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        seven.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        eight.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        nine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        ten.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        eleven.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        twelve.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        half.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        twentyTo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        quarterTo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        tenTo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        fiveTo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        past.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));
        to.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textNotLitUp));



        //THE CALENDAR.HOUR is based on the emulators time.
        if (Calendar.AM == amOrPm) {
            pm.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        } else if (Calendar.PM == amOrPm) {
            am.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        }

        if (9 == hour && min >= 35 || 10 == hour && min < 35){
            ten.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        }
        else if(10 == hour && min >= 35 || 11 == hour && min < 35) {
            eleven.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        }
        else if(11 == hour && min >= 35 || 12 == hour && min < 35) {
            twelve.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        }
        else if(12 == hour && min >= 35 || 1 == hour && min < 35) {
                one.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
            }
        else if(1 == hour && min >= 35 || 2 == hour && min < 35) {
                two.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
            }
        else if(2 == hour && min >= 35 || 3 == hour && min < 35) {
                three.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
            }
        else if(3 == hour && min >= 35 || 4 == hour && min < 35) {
                four.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
            }
        else if(4 == hour && min >= 35 || 5 == hour && min < 35) {
                five.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
            }
        else if(5 == hour && min >= 35 || 6 == hour && min < 35) {
                six.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
            }
        else if(6 == hour && min >= 35 || 7 == hour && min < 35) {
            seven.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        }
        else if(7 == hour && min >= 35 || 8 == hour && min < 35) {
            eight.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        }
        else if(8 == hour && min >= 35 || 9 == hour && min < 35) {
            nine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        }

        if(min >= 35)
            to.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        else if(min < 30 && min >= 5)
            past.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));

        if(min >= 30 && min < 35)
            half.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        else if(min >= 20 && min < 30  || min >= 40 && min < 45)
            twentyTo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        else if(min >= 5 && min < 10  || min >= 55 && min < 60)
            fiveTo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        else if(min >= 15 && min < 20  || min >= 45 && min < 50)
            quarterTo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        else if(min >= 10 && min < 15  || min >= 50 && min < 55)
            tenTo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        else if(min >= 25 && min < 30  || min >= 35 && min < 40) {
            twentyTo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
            fiveTo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textLitUp));
        }
    }

}

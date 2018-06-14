package com.example.lekshmi.kwa_new;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class MakeCall extends AppCompatActivity
{
    public void makecall()
    {
        Intent i=new Intent(Intent.ACTION_CALL, Uri.parse("TEL:8943444242"));
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);
            }
            else
            {
                startActivity(i);
            }
        }
        else
        {
            startActivity(i);
        }
    }
}

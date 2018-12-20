package com.maxamtech.emoji

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkOverlay()
        grantPermission()

    }

    private fun checkOverlay() {
       var intent: Intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
               Uri.parse("package:"+packageName))
        
    }

    private fun grantPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(Settings.System.canWrite(this)){
                //Do stuff here
            }else
            {
                var intent:Intent = Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.setData(Uri.parse("package:"+packageName))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }


        }
    }
}

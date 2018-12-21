package com.maxamtech.emoji

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.widget.Toast
import java.util.jar.Manifest

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkOverlay()
//        grantPermission()

    }

    private fun checkOverlay() {
        if(Build.VERSION.SDK_INT >= 23){
            if(!Settings.canDrawOverlays(this)){
                var builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Permissions")
                builder.setMessage("Please enable the permission for Apnaji app to draw over other app")
                builder.setPositiveButton("Enable", DialogInterface.OnClickListener { dialogInterface, i ->
                    var intent: Intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:"+packageName))
                    startActivityForResult(intent,0)
                    dialogInterface.dismiss()
                })
                builder.setCancelable(false)
                builder.create().show()
            } else grantPermission()
        }

    }

    private fun grantPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            var accessEnable : Int = 0
            var service:String = packageName + "/" + MyAccessibilityService::class.simpleName
            try{
                accessEnable = Settings.Secure.getInt(contentResolver, android.provider.Settings.Secure.ACCESSIBILITY_ENABLED)
            }catch (e : Settings.SettingNotFoundException){
                e.printStackTrace()
            }
            if(accessEnable == 1){
                //Do stuff here
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    storagePermission()
                }else{
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                }

            }else
            {
                var builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Permissions")
                builder.setMessage("Please enable the permission for Apnaji app to accessibility option of android system")
                builder.setPositiveButton("Enable", DialogInterface.OnClickListener { dialogInterface, i ->
                    var intent:Intent = Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS)
                    startActivityForResult(intent,1)
                    dialogInterface.dismiss()
                })
                builder.setCancelable(false)
                builder.create().show()

            }


        }
    }

    private fun storagePermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }else{
                var str = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this,
                        str,
                        3)
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }else {
            Toast.makeText(this, "This is mandatory to enable read write permission", Toast.LENGTH_LONG).show()
            storagePermission()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0){
            checkOverlay()
        }else if(requestCode == 1) grantPermission()
    }
}

package com.maxamtech.emoji;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

import androidapp.test.com.onscreenalertnotify.RecyclerTouchListener;
import androidapp.test.com.onscreenalertnotify.StickerAdapter;

/**
 * Created by Natarajan on 15-06-2015.
 */
public class HUD extends Service {

    private WindowManager windowManager;
    private ImageView close, appIcon;
    private CardView chatheadView;
    private RecyclerView recyclerView;

    @Override
    public IBinder onBind(Intent intent) {
        // Not used
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH ,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;
        chatheadView = (CardView) inflater.inflate(R.layout.emoji_keyboard, null);
        close=(ImageView)chatheadView.findViewById(R.id.imgCloseIcon);
        appIcon = (ImageView) chatheadView.findViewById(R.id.imgEdtIcon);
        recyclerView=(RecyclerView)chatheadView.findViewById(R.id.recyclerView);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new StickerAdapter());
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(@NotNull View view, int position) {

                        stopService(new Intent(getApplicationContext(), HUD.class));
                        callShareOption((ImageView) view);
                    }

                    @Override
                    public void onLongClick(@Nullable View view, int position) {

                    }
                }));


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowManager.removeView(chatheadView);

                stopService(new Intent(getApplicationContext(), HUD.class));
//                startService(new Intent(getApplicationContext(), HUD.class));
            }
        });

        windowManager.addView(chatheadView, params);
        dragDrop();
    }

    private void callShareOption(ImageView imageView) {
        Drawable drawable = getDrawable(R.drawable.ic_demo4);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_demo4);

        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap,
                "Demo_image", null);
        Uri uri = Uri.parse(path);
        try {
            Intent email = new Intent(Intent.ACTION_SEND);


            //need this to prompts email client only
            email.setType("image/jpeg");
            email.setPackage("com.whatsapp");
            email.putExtra(Intent.EXTRA_STREAM, uri);
            email.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(email);//Intent.createChooser(email, "Choose an Email client :"));

        }catch (Exception e){
            Toast.makeText(this, "Whats app not installed", Toast.LENGTH_SHORT).show();
        }
    }

    int xOffset = 0, yOffset = 0, x = 0, y = 0;

    public void dragDrop(){


        appIcon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    //* You can play around with the offset to set where you want the users finger to be on the view. Currently it should be centered.*//*
                    xOffset = v.getWidth()/2;
                    yOffset = v.getHeight()/2;
                    x = (int) event.getRawX() - xOffset;
                    y = (int) event.getRawY() - yOffset;
                    WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                            WindowManager.LayoutParams.WRAP_CONTENT,
                            WindowManager.LayoutParams.WRAP_CONTENT,
                            x,y,
                            WindowManager.LayoutParams.TYPE_PHONE ,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                            PixelFormat.TRANSLUCENT);
                    params.gravity = Gravity.TOP | Gravity. LEFT;
                    windowManager.updateViewLayout(chatheadView, params);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       /* if (chatheadView != null) windowManager.removeView(chatheadView);*/
    }
}


package com.proyecto.univalle.proyectomoviles;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends Activity {
    private final int DURACION_SPLASH = 2000;

    private ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img = findViewById(R.id.log);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.transicion);
        img.startAnimation(anim);
        final Intent i = new Intent(SplashActivity.this, ActivityLogin.class);
        Thread t = new Thread(){
            public void run(){
                try {
                    sleep(2500);

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };

        t.start();

    }
}
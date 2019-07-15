package com.example.anirudhrv.instagram;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity  {

    private int currentApiVersion;
    FragmentEvents fragmentevents = new FragmentEvents();
    FragmentExample fragmentexample = new FragmentExample();
    ImageView img;
    TextView tvpage;

    private GestureDetectorCompat gestureDetectorCompat = null;

    float x1, y1;
    float x2, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //TODO your background code
                currentApiVersion = android.os.Build.VERSION.SDK_INT;

                final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

                // This work only for android 4.4+
                if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

                    getWindow().getDecorView().setSystemUiVisibility(flags);

                    // Code below is to handle presses of Volume up or Volume down.
                    // Without this, after pressing volume buttons, the navigation bar will
                    // show up and won't hide
                    final View decorView = getWindow().getDecorView();
                    decorView
                            .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                                @Override
                                public void onSystemUiVisibilityChange(int visibility) {
                                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                        decorView.setSystemUiVisibility(flags);
                                    }
                                }
                            });
                }
            }
        });


        // start swipe :

        // Create a common gesture listener object.
        MainActivityGesture gestureListener = new MainActivityGesture();

        // Set activity in the listener.
        gestureListener.setActivity(this);

        // Create the gesture detector with the gesture listener.
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);


        LinearLayout ll1;
        ll1 = findViewById(R.id.ll1);


        registerForContextMenu(ll1);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cnt_mnu_event:
                // your first action code
                Toast.makeText(MainActivity.this, "T O  E V E N T S", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cnt_mnu_work:
                // your second action code
                Toast.makeText(MainActivity.this, "T O  W O R K  S H O P", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,EventsActivity.class);
                startActivity(i);
                finish();

                return true;
            case R.id.cnt_mnu_home:
                Toast.makeText(MainActivity.this, "T O  H O M E", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass activity on touch event to the gesture detector.
        gestureDetectorCompat.onTouchEvent(event);
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true;
    }


    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}

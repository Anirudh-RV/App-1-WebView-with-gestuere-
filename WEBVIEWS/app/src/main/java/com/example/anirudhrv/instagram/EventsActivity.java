package com.example.anirudhrv.instagram;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class EventsActivity extends AppCompatActivity {

    private int currentApiVersion;
    float x1,y1;
    float x2,y2;

    // This textview is used to display swipe or tap status info.
    private TextView textView = null;

    // This is the gesture detector compat instance.
    private GestureDetectorCompat gestureDetectorCompat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
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
                if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
                {

                    getWindow().getDecorView().setSystemUiVisibility(flags);

                    // Code below is to handle presses of Volume up or Volume down.
                    // Without this, after pressing volume buttons, the navigation bar will
                    // show up and won't hide
                    final View decorView = getWindow().getDecorView();
                    decorView
                            .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                            {

                                @Override
                                public void onSystemUiVisibilityChange(int visibility)
                                {
                                    if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                                    {
                                        decorView.setSystemUiVisibility(flags);
                                    }
                                }
                            });
                }
            }
        });


        // start swipe :

        textView = (TextView)findViewById(R.id.detect_swipe_direction_textview);

        // Create a common gesture listener object.
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();

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
                Toast.makeText(EventsActivity.this, "T O  E V E N T S", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EventsActivity.this,MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_left,  R.anim.no_animation);
                finish();

                return true;
            case R.id.cnt_mnu_work:
                // your second action code
                Toast.makeText(EventsActivity.this, "T O  W O R K  S H O P", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cnt_mnu_home:
                Toast.makeText(EventsActivity.this, "T O  H O M E", Toast.LENGTH_SHORT).show();
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

    public void displayMessage(String message)
    {
        if(textView!=null) {
            // Display text in the text view.
            textView.setText(message);
        }


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

}

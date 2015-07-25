package com.example.manumaheshwari.gcm_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gcm.GCMRegistrar;
import com.crashlytics.android.Crashlytics;

import java.util.concurrent.TimeUnit;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    String TAG = "GCM Tutorial::Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        final EditText regId = (EditText) findViewById(R.id.regid);
        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);

        // Register Device Button
        Button regbtn = (Button) findViewById(R.id.register);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Registering device");
                // Retrive the sender ID from GCMIntentService.java
                // Sender ID will be registered into GCMRegistrar
                GCMRegistrar.register(MainActivity.this,
                        GCMIntentService.SENDER_ID);
                Log.d("hello", GCMRegistrar.getRegistrationId(getApplicationContext()));

                regId.setText(GCMRegistrar.getRegistrationId(getApplicationContext()));
                long timeMillis = System.currentTimeMillis();
                long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
                Log.d("time", String.valueOf(timeSeconds) +"\n"+ String.valueOf(timeMillis));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

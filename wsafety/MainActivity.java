package com.vinayak09.wsafety;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Map;
import java.util.function.BiConsumer;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String ENUM = sharedPreferences.getString("ENUM","NONE");
        if(ENUM.equalsIgnoreCase("NONE")){
            startActivity(new Intent(this,RegisterNumberActivity.class));
        }else {
            TextView textView =  findViewById(R.id.textNum);
            textView.setText("SOS Will Be Sent To Saved Numbers\n");
        }
       /* SharedPreferences sharedPreferences2 = getSharedPreferences("MySharedPref2",MODE_PRIVATE);
        String ENUM2 = sharedPreferences2.getString("ENUM2","NONE");
        if(ENUM2.equalsIgnoreCase("NONE")){
            startActivity(new Intent(this,RegisterNumberActivity.class));
        }else {
            TextView textView =  findViewById(R.id.textNum);
            textView.setText("SOS Will Be Sent To\n"+ENUM2);
        }
        SharedPreferences sharedPreferences3 = getSharedPreferences("MySharedPref3",MODE_PRIVATE);
        String ENUM3 = sharedPreferences3.getString("ENUM3","NONE");
        if(ENUM3.equalsIgnoreCase("NONE")){
            startActivity(new Intent(this,RegisterNumberActivity.class));
        }else {
            TextView textView =  findViewById(R.id.textNum);
            textView.setText("SOS Will Be Sent To\n"+ENUM3);
        }
        SharedPreferences sharedPreferences4 = getSharedPreferences("MySharedPref4",MODE_PRIVATE);
        String ENUM4 = sharedPreferences4.getString("ENUM4","NONE");
        if(ENUM4.equalsIgnoreCase("NONE")){
            startActivity(new Intent(this,RegisterNumberActivity.class));
        }else {
            TextView textView =  findViewById(R.id.textNum);
            textView.setText("SOS Will Be Sent To\n"+ENUM4);
        }
        SharedPreferences sharedPreferences5 = getSharedPreferences("MySharedPref5",MODE_PRIVATE);
        String ENUM5 = sharedPreferences5.getString("ENUM","NONE");
        if(ENUM5.equalsIgnoreCase("NONE")){
            startActivity(new Intent(this,RegisterNumberActivity.class));
        }else {
            TextView textView =  findViewById(R.id.textNum);
            textView.setText("SOS Will Be Sent To\n"+ENUM5);
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("MYID", "CHANNELFOREGROUND", NotificationManager.IMPORTANCE_DEFAULT);

                NotificationManager m = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                m.createNotificationChannel(channel);
            }
        }



        
    }


    private ActivityResultLauncher<String[]> multiplePermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
        @Override
        public void onActivityResult(Map<String, Boolean> result) {

            for (Map.Entry<String,Boolean> entry : result.entrySet())
                if(!entry.getValue()){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Permission Must Be Granted!", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("Grant Permission", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            multiplePermissions.launch(new String[]{entry.getKey()});
                            snackbar.dismiss();
                        }
                    });
                    snackbar.show();
                }


        }

    });



    public void stopService(View view) {

        Intent notificationIntent = new Intent(this,ServiceMine.class);
        notificationIntent.setAction("stop");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getApplicationContext().startForegroundService(notificationIntent);
            Snackbar.make(findViewById(android.R.id.content),"Service Stopped!", Snackbar.LENGTH_LONG).show();
        }
    }

    public void startServiceV(View view) {





        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED  ) {
            Intent notificationIntent = new Intent(this,ServiceMine.class);
            notificationIntent.setAction("Start");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                getApplicationContext().startForegroundService(notificationIntent);
                Snackbar.make(findViewById(android.R.id.content),"Service Started!", Snackbar.LENGTH_LONG).show();
            }
        }else{
            multiplePermissions.launch(new String[]{Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION});
        }

    }

    public void PopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.changeNum){
                    startActivity(new Intent(MainActivity.this,RegisterNumberActivity.class));
                }
                return true;
            }
        });
        popupMenu.show();
    }
}
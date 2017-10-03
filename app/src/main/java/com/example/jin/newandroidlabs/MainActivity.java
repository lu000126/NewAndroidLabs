package com.example.jin.newandroidlabs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends Activity {

    protected static final String ACTIVITY_NAME = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        setContentView(R.layout.activity_main);

        Button imButton = (Button) findViewById(R.id.button);

        imButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListItemActivity.class);
                startActivityForResult(intent,10);
            }
        });
        Button chatButton = (Button) findViewById(R.id.chatbutton);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i(ACTIVITY_NAME,"User clicked Start Chat");
                Intent intent = new Intent(MainActivity.this,ChatWindow.class);
                startActivity(intent);
            }
        });

    }

    protected void onActivityResult(int requestCode,int responeseCode,Intent data){
        if (requestCode==10) {
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
        }
        if (responeseCode==Activity.RESULT_OK){
            String messagePassed = data.getStringExtra("Response");
                    /*CharSequence text = "ListItemActivity passed: My information to share";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(MainActivity.this , text, duration); //this is the ListActivity

                    toast.show();*/
            Toast.makeText(MainActivity.this, "ListItemActivity passed: My information to share", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
    /*
    public void onListButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this,ListItemActivity.class);
        startActivityForResult(intent,10);
    }

    public void onActivityResult(int requestCode,int responeseCode,Intent data){
        if (requestCode==10) {
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
        }
    }
    */
}

package com.akashsharma.demoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HomePageActivity extends AppCompatActivity {
    Button next;
    ImageView img;
    int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toast.makeText(HomePageActivity.this,"Hi, "+getIntent().getStringExtra("name")+" !",Toast.LENGTH_LONG).show();
        next= (Button) findViewById(R.id.nextbtn);
        img= (ImageView) findViewById(R.id.imgview);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cnt==0)
                {
                    img.setImageResource(R.drawable.userinterface1655006_960_720);
                    cnt++;
                    Log.i("Test","1st img");
                }
                else
                {
                    cnt--;
                    img.setImageResource(R.drawable.appfeatures);
                    Log.i("Test","2nd img");

                }

            }
        });



    }
}

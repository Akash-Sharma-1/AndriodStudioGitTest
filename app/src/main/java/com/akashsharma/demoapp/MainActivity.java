package com.akashsharma.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private TextView attemptsleft;
    private Button login;
    private Button register;
    private EditText username;
    private EditText password;
    private int attemptscnt=5;
    private HashMap<String,String> database=new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.inpname);
        password = findViewById(R.id.inppass);
        attemptsleft= findViewById(R.id.attempts);
        login= findViewById(R.id.loginbutton);
        register= findViewById(R.id.registerbutton);
        attemptsleft.setText(attemptsleft.getText().toString()+" "+Integer.toString(attemptscnt));
        addnewdata("akash","1234");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search(username.getText().toString(),password.getText().toString()))
                {
                    Intent homepage=new Intent(MainActivity.this,HomePageActivity.class);
                    startActivity(homepage);
                }
                else
                {
                    if(attemptscnt==0)
                    {
                        attemptsleft.setText("No. of Attempts left: "+Integer.toString(attemptscnt));
                        login.setEnabled(false);
                    }
                    else
                    {
                        attemptscnt--;
                        attemptsleft.setText("No. of Attempts left: "+Integer.toString(attemptscnt));
                    }


                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration = new Intent(MainActivity.this,RegisterPageActivity.class);
                startActivity(registration);
            }
        });

        if(getIntent().getStringExtra("name") != null)
        {
            if(!database.containsKey(getIntent().getStringExtra("name")))
            {
                addnewdata(getIntent().getStringExtra("name"),getIntent().getStringExtra("pass"));
                System.out.println(database);
            }
            else
            {
                Intent registration = new Intent(MainActivity.this,RegisterPageActivity.class);
                registration.putExtra("ERROR","Sorry!! This username  already exists");
                startActivity(registration);
            }

        }
    }

    private boolean search(String inp1,String inp2)
    {
        if(database.containsKey(inp1))
        {
            return database.get(inp1).equals(inp2);
        }
        else
        {
            return  false;
        }
    }

    public void addnewdata(String inp1,String inp2)
    {
        database.put(inp1,inp2);
    }
}

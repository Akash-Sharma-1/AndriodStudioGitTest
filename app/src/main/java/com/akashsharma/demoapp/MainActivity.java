package com.akashsharma.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
class database
{
    private static HashMap<String,String> userslist=new HashMap<String, String>();
    public boolean search(String inp1,String inp2)
    {
        if(userslist.containsKey(inp1))
        {
            return userslist.get(inp1).equals(inp2);
        }
        else
        {
            return  false;
        }
    }

    public void addnewdata(String inp1,String inp2)
    {
        userslist.put(inp1,inp2);
    }

    public boolean availability(String s)
    {
        return userslist.containsKey(s);
    }
    public void print()
    {
        System.out.println(userslist);
    }

}
public class MainActivity extends AppCompatActivity {
    private TextView attemptsleft;
    private Button login;
    private Button register;
    private EditText username;
    private EditText password;
    private int attemptscnt=5;
    database d=new database();



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
        d.addnewdata("akash","1234");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(d.search(username.getText().toString(),password.getText().toString()))
                {
                    Intent homepage=new Intent(MainActivity.this,HomePageActivity.class);
                    homepage.putExtra("name",username.getText().toString());
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
            if(!d.availability(getIntent().getStringExtra("name")))
            {
                d.addnewdata(getIntent().getStringExtra("name"),getIntent().getStringExtra("pass"));
//                System.out.println(d);
                d.print();
            }

            else
            {
                Intent registration = new Intent(MainActivity.this,RegisterPageActivity.class);
                registration.putExtra("ERROR","Sorry!! This username  already exists");
                startActivity(registration);
            }

        }
    }


}

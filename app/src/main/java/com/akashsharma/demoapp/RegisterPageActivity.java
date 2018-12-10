package com.akashsharma.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterPageActivity extends AppCompatActivity {
    private EditText reguser;
    private EditText regpass;
    private Button save;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        reguser= findViewById(R.id.ruser);
        regpass= findViewById(R.id.rpass);
        save = findViewById(R.id.savebtn);
        error = findViewById(R.id.Error);
        if(getIntent().getStringExtra("ERROR")!=null)
        {
            error.setText(getIntent().getStringExtra("ERROR"));
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent main = new Intent(RegisterPageActivity.this,MainActivity.class);
                main.putExtra("name",reguser.getText().toString());
                main.putExtra("pass",regpass.getText().toString());
                startActivity(main);
            }
        });
    }
}

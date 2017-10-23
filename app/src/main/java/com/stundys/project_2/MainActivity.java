package com.stundys.project_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "sendMessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, ColorChanger.class);
        EditText input = (EditText) findViewById(R.id.editText);
        String value = input.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, value);
        startActivity(intent);
    }
}

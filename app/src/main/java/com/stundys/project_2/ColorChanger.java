package com.stundys.project_2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.sql.Array;

public class ColorChanger extends AppCompatActivity {

    boolean IsChangeColors = false;

    protected void setColorState(boolean state) {
        this.IsChangeColors = state;

        if(state == true){
            EditText field = (EditText) findViewById(R.id.number_field);
            changeColor(field.getText());
        }
    }

    boolean getColorState() {
        return this.IsChangeColors;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_changer);

        greetUser();

        final EditText numberField = (EditText)  findViewById(R.id.number_field);
        Switch switcher = (Switch)  findViewById(R.id.switch1);
        final View colorViewer = findViewById(R.id.colorViewer);
        final TextView numberStatus = (TextView) findViewById(R.id.number_status);




        numberField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable field) {
                String x = field.toString();


                x = x.replaceAll(".*(?=\\d)", "");
                if(x.startsWith("0") || x == ""){
                    numberField.setText("");
                    return;
                }

                changeColor(field);
            }
        });
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                setColorState(true);
            } else {
                setColorState(false);
            }
            }
        });
    }

    protected void changeColor(Editable field){
        final View colorViewer = (View) findViewById(R.id.colorViewer);
        final TextView numberStatus = (TextView) findViewById(R.id.number_status);

        int[] colors = {
                ResourcesCompat.getColor(getResources(), R.color.yellow, null),
                ResourcesCompat.getColor(getResources(), R.color.green, null),
                ResourcesCompat.getColor(getResources(), R.color.cyan, null),
                ResourcesCompat.getColor(getResources(), R.color.purple, null),
                ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null),
        };

        try {
            int value = Integer.parseInt(field.toString()),
                    selectedColor;

            if(value % 5 == 0) {
                numberStatus.setText(R.string.denominator_5);
                selectedColor = colors[0];
            } else if (value % 4 == 0) {
                numberStatus.setText(R.string.denominator_4);
                selectedColor = colors[1];
            } else if (value % 3 == 0) {
                numberStatus.setText(R.string.denominator_3);
                selectedColor = colors[2];
            } else if (value % 2 == 0) {
                numberStatus.setText(R.string.denominator_2);
                selectedColor = colors[3];
            } else {
                numberStatus.setText(R.string.simple_number);
                selectedColor = colors[4];
            }

            if(getColorState()) {
                colorViewer.setBackgroundColor(selectedColor);
            }
        } catch (NumberFormatException e) {
            numberStatus.setText(R.string.enter_number);
            if(getColorState()) colorViewer.setBackgroundColor(0);
        }
    }

    protected void greetUser(){
        Intent intent = getIntent();

        String greeting = getString(R.string.hello),
                message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        if(!message.isEmpty()){
            greeting += ", " + message;
        }

        greeting += "!";

        TextView greeter = (TextView) findViewById(R.id.textView4);
        greeter.setText(greeting);
    }
}

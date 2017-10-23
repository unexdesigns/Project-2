package com.stundys.project_2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
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
        final Switch switcher = (Switch)  findViewById(R.id.switch1);
        final TextView numberStatus = (TextView) findViewById(R.id.number_status);



        numberField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String x = editable.toString();
                View colorViewer = (View) findViewById(R.id.colorViewer);

                x = x.replaceAll(".*(?=\\d)", "");
                if(x.startsWith("0") || x == ""){
                    numberField.setText("");
                    return;
                }

                try {
                    Integer value = Integer.parseInt(editable.toString());



                    int selectedColor = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);

                    if(value % 5 == 0) {
                        numberStatus.setText(R.string.denominator_5);
                        selectedColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
                    } else if (value % 4 == 0) {
                        numberStatus.setText(R.string.denominator_4);
                        selectedColor = ContextCompat.getColor(getApplicationContext(), R.color.yellow);
                    } else if (value % 3 == 0) {
                        numberStatus.setText(R.string.denominator_3);
                        selectedColor = ContextCompat.getColor(getApplicationContext(), R.color.green);
                    } else if (value % 2 == 0) {
                        numberStatus.setText(R.string.denominator_2);
                        selectedColor = ContextCompat.getColor(getApplicationContext(), R.color.purple);
                    } else {
                        numberStatus.setText(R.string.simple_number);
                        selectedColor = ContextCompat.getColor(getApplicationContext(), R.color.cyan);
                    }

                    if(getColorState()) colorViewer.setBackgroundColor(selectedColor);

                } catch (NumberFormatException e) {
                    numberStatus.setText(R.string.enter_number);
                    if(getColorState()) colorViewer.setBackgroundColor(0);
                }
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

package com.vinayak09.wsafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterNumberActivity extends AppCompatActivity {

    TextInputEditText number1,number2,number3,number4,number5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_number);

        number1 = findViewById(R.id.numberEdit1);
        number2 = findViewById(R.id.numberEdit2);
        number3 = findViewById(R.id.numberEdit3);
        number4 = findViewById(R.id.numberEdit4);
        number5 = findViewById(R.id.numberEdit5);
    }

    public void saveNumber(View view) {
        String numberString1 = number1.getText().toString();
        String numberString2 = number2.getText().toString();
        String numberString3 = number3.getText().toString();
        String numberString4 = number4.getText().toString();
        String numberString5 = number5.getText().toString();
        if(numberString1.length()==10){
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("ENUM", numberString1);
            myEdit.putString("ENUM2", numberString2);
            myEdit.putString("ENUM3", numberString3);
            myEdit.putString("ENUM4", numberString4);
            myEdit.putString("ENUM5", numberString5);
            myEdit.apply();
            RegisterNumberActivity.this.finish();
        }else {
            Toast.makeText(this, "Enter Valid Number!", Toast.LENGTH_SHORT).show();
        }
       /* if(numberString2.length()==10){
            SharedPreferences sharedPreferences2 = getSharedPreferences("MySharedPref2",MODE_PRIVATE);
            SharedPreferences.Editor myEdit2 = sharedPreferences2.edit();
            myEdit2.putString("ENUM2", numberString2);
            myEdit2.apply();
            RegisterNumberActivity.this.finish();
        }else {
            Toast.makeText(this, "Enter Valid Number!", Toast.LENGTH_SHORT).show();
        }
        if(numberString3.length()==10){
            SharedPreferences sharedPreferences3 = getSharedPreferences("MySharedPref3",MODE_PRIVATE);
            SharedPreferences.Editor myEdit3 = sharedPreferences3.edit();
            myEdit3.putString("ENUM3", numberString3);
            myEdit3.apply();
            RegisterNumberActivity.this.finish();
        }else {
            Toast.makeText(this, "Enter Valid Number!", Toast.LENGTH_SHORT).show();
        }
        if(numberString4.length()==10){
            SharedPreferences sharedPreferences4 = getSharedPreferences("MySharedPref4",MODE_PRIVATE);
            SharedPreferences.Editor myEdit4 = sharedPreferences4.edit();
            myEdit4.putString("ENUM4", numberString4);
            myEdit4.apply();
            RegisterNumberActivity.this.finish();
        }else {
            Toast.makeText(this, "Enter Valid Number!", Toast.LENGTH_SHORT).show();
        }
        if(numberString5.length()==10){
            SharedPreferences sharedPreferences5 = getSharedPreferences("MySharedPref5",MODE_PRIVATE);
            SharedPreferences.Editor myEdit5 = sharedPreferences5.edit();
            myEdit5.putString("ENUM5", numberString5);
            myEdit5.apply();
            RegisterNumberActivity.this.finish();
        }else {
            Toast.makeText(this, "Enter Valid Number!", Toast.LENGTH_SHORT).show();
        }*/
    }
}
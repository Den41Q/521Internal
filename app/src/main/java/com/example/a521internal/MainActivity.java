package com.example.a521internal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText mLoginEdTxt;
    private EditText mPassEdTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginEdTxt = findViewById(R.id.login);
        mPassEdTxt = findViewById(R.id.pass);
    }

    public void checkData(View view) {
        try {
            String myLogin = "Den41Q";
            String myPass = "qwertY";

            FileInputStream fileInputStream = openFileInput("Login");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String login = reader.readLine();

            FileInputStream fileInputStream1 = openFileInput("Pass");
            InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1);
            BufferedReader reader1 = new BufferedReader(inputStreamReader1);
            String pass = reader1.readLine();

            if (login.equalsIgnoreCase(myLogin) & pass.equals(myPass)) {
                Toast.makeText(this, "Логин и пароль верные",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Неправильный логин или пароль!",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData(View view) {
        String login = mLoginEdTxt.getText().toString();
        String pass = mPassEdTxt.getText().toString();

        if (login.equalsIgnoreCase("") |
                pass.equalsIgnoreCase("")) {
            Toast.makeText(this, "Заполните все поля!",
                    Toast.LENGTH_SHORT).show();
        }

        try {
            FileOutputStream fos = openFileOutput("Login", Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);

            FileOutputStream fos1 = openFileOutput("Pass", Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter1 = new OutputStreamWriter(fos1);
            BufferedWriter bw1 = new BufferedWriter(outputStreamWriter1);

            bw.write(login);
            bw1.write(pass);
            bw.close();
            bw1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
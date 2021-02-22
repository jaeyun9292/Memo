package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity{
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragmentlist fragmentlist = new Fragmentlist();
        transaction.replace(R.id.memofragment,fragmentlist);
        transaction.commit();


    }

    public void fragBtnClick(Bundle bundle) {
        this.bundle = bundle;
    }
}
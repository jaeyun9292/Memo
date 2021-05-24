package com.example.memo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


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

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.memofragment,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
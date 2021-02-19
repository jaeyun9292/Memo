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

        SharedPreferences sp;
        sp = getSharedPreferences("memo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

//        Button btn_add = (Button)findViewById(R.id.btn_add);
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int listsize = arrayList.size();
//                for (int i = 0; i <= listsize; i++) {
//                    if(listsize == i){
//                        write += 1;
//                        editor.putString("memotitle"+i,"제목"+write);
//                        editor.putString("memocontent"+i,"내용"+write);
//                        editor.putInt("count", i+1);
//                        editor.commit();
//
//                    }
//                }MainData mainData = new MainData("제목"+write,"내용"+write);
//                arrayList.add(mainData);
//                memoAdapter.notifyDataSetChanged();
//
//            }
//        });
    }

    public void fragBtnClick(Bundle bundle) {
        this.bundle = bundle;
    }
}
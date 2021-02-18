package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClick{
    private ArrayList<MainData> arrayList;
    private MemoAdapter memoAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        arrayList = new ArrayList<>();
        memoAdapter = new MemoAdapter(arrayList,this);
        recyclerView.setAdapter(memoAdapter);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragmentlist fragmentlist = new Fragmentlist();
        transaction.replace(R.id.memofragment,fragmentlist);
        transaction.commit();


        Button btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData mainData = new MainData("제목","내용");
                arrayList.add(mainData);
                memoAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(int value) {

    }
}
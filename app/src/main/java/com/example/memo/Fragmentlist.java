package com.example.memo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Fragmentlist extends Fragment implements OnItemClick{
    public ArrayList<MainData> arrayList = new ArrayList<>();
    public MemoAdapter memoAdapter = null;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    Context context;
    MainActivity activity;

    int write;

    public Fragmentlist(){}

    @Override
    public void onAttach(@NonNull Context context) {            //프래그먼트와 액티비티가 연결될때 호출되는 메소드
        super.onAttach(context);
        this.context = context;
        activity = (MainActivity)getActivity();
        Log.i("onAttach: ","!!!!!");
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("onCreateView: ","!!!!!");

        View view = inflater.inflate(R.layout.memo_list_fragment, container, false);
        recyclerView = view.findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        SharedPreferences sp;
        sp = context.getSharedPreferences("memo",MODE_PRIVATE);
        int count = sp.getInt("count",0);

        SharedPreferences.Editor editor = sp.edit();
        Button btn_add = (Button)activity.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <= count; i++) {
                    if(count == i){
                        write = i+1;
                        editor.putString("memotitle"+i,"제목"+write);
                        editor.putString("memocontent"+i,"내용"+write);
                        editor.putInt("count", i+1);
                        editor.commit();

                        MainData mainData = new MainData("제목"+write,"내용"+write);
                        arrayList.add(mainData);
                        memoAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


        for (int i = 0; i <count; i++) {
            String title = sp.getString("memotitle"+i,"");
            String content = sp.getString("memocontent"+i,"");
            MainData mainData = new MainData(title, content);
            arrayList.add(mainData);
        }




        memoAdapter = new MemoAdapter(arrayList, this);
        recyclerView.setAdapter(memoAdapter);

        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        arrayList.clear();
    }

    @Override
    public void onClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        activity.fragBtnClick(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragmentdetail fragmentdetail = new Fragmentdetail();
        transaction.replace(R.id.memofragment,fragmentdetail);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}

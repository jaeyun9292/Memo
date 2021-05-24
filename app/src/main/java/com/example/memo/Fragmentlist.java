package com.example.memo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Fragmentlist extends Fragment implements OnItemClick {
    public ArrayList<MainData> arrayList = new ArrayList<>();
    public MemoAdapter memoAdapter = null;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    Context context;
    MainActivity activity;
    int count;
    String title;

    public Fragmentlist() {
    }

    @Override
    public void onAttach(@NonNull Context context) {            //프래그먼트와 액티비티가 연결될때 호출되는 메소드
        super.onAttach(context);
        this.context = context;
        activity = (MainActivity) getActivity();
        Log.i("onAttach: ", "!!!!!");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("onCreateView: ", "111111111111111");
        View view = inflater.inflate(R.layout.memo_list_fragment, container, false);
        recyclerView = view.findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        SharedPreferences sp;
        sp = context.getSharedPreferences("memo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        count = sp.getInt("count", 1);

        for (int i = 1; i <= count; i++) {
            String title = sp.getString(String.valueOf(i), "");
            if (!title.equals("")) {
                MainData mainData = new MainData(title);
                arrayList.add(mainData);
            }
        }

        memoAdapter = new MemoAdapter(arrayList, this);
        recyclerView.setAdapter(memoAdapter);


        FloatingActionButton btn_add = (FloatingActionButton) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(context);
                ad.setTitle("제목");       // 제목 설정

                // EditText 삽입하기
                final EditText et = new EditText(context);
                if (et.getParent() != null)
                    ((ViewGroup) et.getParent()).removeView(et);
                ad.setView(et);

                // 확인 버튼 설정
                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        count = sp.getInt("count", 1);
                        editor.putInt("count", count + 1);
                        String title = " " + et.getText().toString();
                        editor.putString(String.valueOf(count), title);
                        editor.commit();
                        MainData mainData = new MainData(title);
                        arrayList.add(mainData);
                        memoAdapter.notifyDataSetChanged();
                        dialog.dismiss();     //닫기
                    }
                });

                // 취소 버튼 설정
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();     //닫기
                    }
                });
                ad.show();
            }
        });

        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        arrayList.clear();
    }

    @Override
    public void onClick(int position) {
        MainData mainData = arrayList.get(position);
        title = mainData.getTitle();
        Bundle bundle = new Bundle();       //번들 어느자료형이나 담을수있음
        bundle.putString("title", title);
        activity.fragBtnClick(bundle);


        Fragmentdetail fragmentdetail = new Fragmentdetail();
        activity.replaceFragment(fragmentdetail);
    }

    @Override
    public void longClick(int position) {
        MainData mainData = arrayList.get(position);
        title = mainData.getTitle();
        Log.i("longClick: ", title);

        SharedPreferences sp;   //프리퍼런스
        sp = context.getSharedPreferences("memo", MODE_PRIVATE);

        String a;
        int i = 0;
        while (true) {
            i++;
            a = sp.getString(String.valueOf(i), "");
            if (a.equals(title)) {
                break;
            }
        }

        SharedPreferences.Editor editor = sp.edit();
        editor.remove(String.valueOf(i));
        editor.commit();
        context.deleteFile(title);
    }
}
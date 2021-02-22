package com.example.memo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Fragmentdetail extends Fragment {
    Context context;
    MainActivity activity;
    String title;

    public Fragmentdetail(){}

    @Override
    public void onAttach(@NonNull Context context) {            //프래그먼트와 액티비티가 연결될때 호출되는 메소드
        super.onAttach(context);
        this.context = context;

        activity = (MainActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = activity.bundle;
        title = bundle.getString("title");

        View v = inflater.inflate(R.layout.memo_detail_fragment, container, false);

        InputStream in = null;
        BufferedReader reader;
        try{
            in = context.openFileInput(title);
        }catch (Exception e){e.printStackTrace();}


        EditText editText = v.findViewById(R.id.detail);

        try{
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String s;
            while ((s = reader.readLine())!=null){
                editText.append(s);
            }
        }catch (Exception e){e.printStackTrace();}

        return  v;
    }

    @Override
    public void onPause() {
        super.onPause();
        EditText editText = activity.findViewById(R.id.detail);
        String s = editText.getText().toString();
        try {
            OutputStream out = context.openFileOutput(title, Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.append(s);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}

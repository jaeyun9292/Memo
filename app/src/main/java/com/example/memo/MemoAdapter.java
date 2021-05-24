package com.example.memo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.CustomViewHolder> {
    private ArrayList<MainData> arrayList;
    private OnItemClick mCallback;

    public MemoAdapter(ArrayList<MainData> arrayList, OnItemClick listener) {
        this.arrayList = arrayList;
        this.mCallback = listener;
    }


    @NonNull
    @Override
    public MemoAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemoAdapter.CustomViewHolder holder, int position) {

        holder.title.setText(arrayList.get(position).getTitle());

        holder.itemView.setTag(position);
        Log.i(TAG, "onBindViewHolder: " + position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onClick(holder.getAdapterPosition());
                Log.i(TAG, "onClick: " + holder.getAdapterPosition());
                String title = holder.title.getText().toString();
                Toast.makeText(v.getContext(), title, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mCallback.longClick(holder.getAdapterPosition());
                remove(position);
                return true;
            }
        });
    }

    public void remove(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
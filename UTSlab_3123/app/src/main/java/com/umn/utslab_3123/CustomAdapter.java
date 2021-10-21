package com.umn.utslab_3123;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.LaguViewHolder> {

    private final LinkedList<String> mDaftarLagu;
    private LayoutInflater mInflater;

    CustomAdapter(Context context,LinkedList<String> daftarLagu){
        mInflater = LayoutInflater.from(context);
        mDaftarLagu = daftarLagu;
    }


    @NonNull
    @Override
    public LaguViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View mItemView = mInflater.inflate(R.layout.raw_sfx,parent,false);
      return new LaguViewHolder(mItemView,this);
       }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.LaguViewHolder holder, int position) {
       String mCurr = mDaftarLagu.get(position);
       holder.judul.setText(mCurr);
    }

    @Override
    public int getItemCount() {
        return mDaftarLagu.size();
    }

    public class LaguViewHolder extends RecyclerView.ViewHolder {
        public final TextView judul;
        final CustomAdapter mAdapter;

       public LaguViewHolder(View itemView,CustomAdapter adapter){
           super(itemView);
          judul = itemView.findViewById(R.id.namalg);
          this.mAdapter = adapter;
       }
    }
}

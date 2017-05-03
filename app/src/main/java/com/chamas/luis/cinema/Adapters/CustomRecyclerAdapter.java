package com.chamas.luis.cinema.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chamas.luis.cinema.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Luis on 5/2/2017.
 */

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    ArrayList<String> picturesUrls;
    Context context;

    public CustomRecyclerAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<String> picturesUrls){
        this.picturesUrls = picturesUrls;
        notifyDataSetChanged();
    }

    @Override
    public CustomRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_poster, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomRecyclerAdapter.ViewHolder holder, int position) {
        Picasso.with(context).load(picturesUrls.get(position)).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return (picturesUrls != null) ? picturesUrls.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}

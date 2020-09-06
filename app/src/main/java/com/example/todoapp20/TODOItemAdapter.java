package com.example.todoapp20;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TODOItemAdapter extends RecyclerView.Adapter<TODOItemAdapter.ViewHolder> {
    List<String> items;
    Context context;
    OnLongClickListener longClickListener;
    OnClickListener onClickListener;

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }
    public interface OnClickListener{
        void onItemClicked(int position);
    }

    public TODOItemAdapter(List<String> items, Context context, OnLongClickListener longClickListener, OnClickListener onClickListener) {
        this.items = items;
        this.context = context;
        this.longClickListener = longClickListener;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(List<String> items){
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clear(){
        this.items.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
            tvName.setText(item);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}

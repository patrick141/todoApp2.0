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

import com.example.todoapp20.models.TODOItem;

import java.util.List;

public class TODOItemAdapter extends RecyclerView.Adapter<TODOItemAdapter.ViewHolder> {
    List<TODOItem> items;
    Context context;
    View.OnLongClickListener onLongClickListener;

    public TODOItemAdapter(List<TODOItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TODOItem item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(List<TODOItem> items){
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clear(){
        this.items.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvName;
        CheckBox status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            status = itemView.findViewById(R.id.checkbox);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bind(TODOItem item) {
            tvName.setText(item.getComment());
            status.setChecked(true);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            TODOItem item = items.get(position);
            Intent intent = new Intent(context,EditActivity.class);
            context.startActivity(intent);
        }


        @Override
        public boolean onLongClick(View view) {
            int position = getAdapterPosition();
            TODOItem item = items.get(position);
            items.remove(item);
            notifyItemRemoved(position);
            //((MainActivity) context).saveItems();
            return true;
        }
    }
}

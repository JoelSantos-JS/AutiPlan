package com.jjtech.autiplan;

import android.content.ContentValues;
import android.content.Context;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.titleText.setText(item.getTitle());
        holder.iconImage.setImageResource(item.getImageResource());

        // Atualizar estado de conclusão
        holder.completionCheck.setChecked(item.isCompleted());

        holder.itemView.setOnClickListener(v -> {
            item.setCompleted(!item.isCompleted());
            holder.completionCheck.setChecked(item.isCompleted());
            // Aqui você pode adicionar animação ou feedback
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(List<Item> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        ImageView iconImage;
        CheckBox completionCheck;

        ViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.task_title);
            iconImage = itemView.findViewById(R.id.task_icon);
            completionCheck = itemView.findViewById(R.id.task_check);
        }
    }
}
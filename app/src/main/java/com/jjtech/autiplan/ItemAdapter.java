package com.jjtech.autiplan;

import android.content.ContentValues;
import android.content.Context;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private List<Item> itemList; // Lista de itens (modelo)

    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar o layout do item (ex: grid_item.xml)
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Configurar os dados do item
        Item item = itemList.get(position);
        holder.imageView.setImageResource(item.getImageResId());
        holder.textView.setText(item.getName());

        // Clique no item
        holder.itemView.setOnClickListener(v ->
                addTaskToCalendar(item.getName())
        );
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            textView = itemView.findViewById(R.id.item_text);
        }
    }


    private  void addTaskToCalendar(String taskName ) {
        Calendar calendar = Calendar.getInstance();
        long startTime = calendar.getTimeInMillis();
        calendar.add(Calendar.HOUR , 1);
        long endTime = calendar.getTimeInMillis();

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.CALENDAR_ID, 1); // Substitua pelo ID do calendário correto
        values.put(CalendarContract.Events.TITLE, taskName);
        values.put(CalendarContract.Events.DESCRIPTION, "Atividade adicionada pelo app");
        values.put(CalendarContract.Events.DTSTART, startTime);
        values.put(CalendarContract.Events.DTEND, endTime);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "UTC");

        try {
            context.getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);
            Toast.makeText(context, "Tarefa '" + taskName + "' adicionada ao calendário!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Erro ao adicionar tarefa ao calendário!", Toast.LENGTH_SHORT).show();
        }

    }
}

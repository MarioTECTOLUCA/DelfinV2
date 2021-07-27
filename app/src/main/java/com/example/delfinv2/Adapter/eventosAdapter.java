package com.example.delfinv2.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delfinv2.Model.Eventos;
import com.example.delfinv2.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

public class eventosAdapter extends RecyclerView.Adapter<eventosAdapter.EventosHolder> {

    private List<Eventos> eventosList;
    private Context context;

    public eventosAdapter(List<Eventos> eventosList,Context context) {
        this.eventosList = eventosList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public EventosHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new EventosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EventosHolder holder, int position) {
        holder.constraintLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in));
        switch (Locale.getDefault().getLanguage()){
            case "es":
                holder.eventName.setText(eventosList.get(position).getNombre_es().toString());
                break;
            case "en":
                holder.eventName.setText(eventosList.get(position).getNombre_en().toString());
                break;
            case "fr":
                holder.eventName.setText(eventosList.get(position).getNombre_fr().toString());
                break;
        }
        switch (eventosList.get(position).getStatus()){
            case "ACTIVO" :
                holder.view.setBackgroundColor(context.getColor(R.color.green));
                break;
            case "PENDIENTE" :
                holder.view.setBackgroundColor(context.getColor(R.color.orange));
                break;
            case "CANCELADA" :
                holder.view.setBackgroundColor(context.getColor(R.color.red));
                break;
            case "PASADA" :
                holder.view.setBackgroundColor(context.getColor(R.color.grey));
                break;
        }
        holder.dateEvent.setText(eventosList.get(position).getFecha().toString());
        holder.hourEvent.setText(eventosList.get(position).getHora_inicio()+" : "+eventosList.get(position).getHora_fin());

    }

    @Override
    public int getItemCount() {
        return eventosList.size();
    }

    public class EventosHolder extends RecyclerView.ViewHolder {

        private TextView eventName, dateEvent, hourEvent ;
        private View view;
        private ConstraintLayout constraintLayout;

        public EventosHolder(@NonNull @NotNull View root) {
            super(root);
            initComponents(root);

        }

        private void initComponents(View root){
            eventName = root.findViewById(R.id.itemnombreEvento);
            dateEvent = root.findViewById(R.id.itemfechaEvento);
            hourEvent = root.findViewById(R.id.itemhorarioEvento);
            view = root.findViewById(R.id.viewStatusBar);
            constraintLayout = root.findViewById(R.id.itemCalendarConstraintLayout);
        }
    }
}

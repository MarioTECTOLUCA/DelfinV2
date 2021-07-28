package com.example.delfinv2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delfinv2.Model.Pregunta;
import com.example.delfinv2.R;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterPreguntas extends RecyclerView.Adapter<AdapterPreguntas.ViewHolderDatos> implements View.OnClickListener {

    private ArrayList<Pregunta> ListPreguntas;
    private Context mContext;
    private View.OnClickListener listener;

    public AdapterPreguntas(ArrayList<Pregunta> listPreguntas, Context mContext) {
        ListPreguntas = listPreguntas;
        this.mContext = mContext;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pregunta,null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.layout.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_in));
        switch (Locale.getDefault().getLanguage()){
            case "es":
                holder.nombre.setText(ListPreguntas.get(position).getPregunta_es());
                break;
            case "en":
                holder.nombre.setText(ListPreguntas.get(position).getPregunta_en());
                break;
            case "fr":
                holder.nombre.setText(ListPreguntas.get(position).getPregunta_fr());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return ListPreguntas.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        private TextView nombre;
        private CardView layout;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.pregunta);
            layout = itemView.findViewById(R.id.resQuestionCardView);
        }
    }
}
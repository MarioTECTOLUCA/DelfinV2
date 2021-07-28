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
import com.example.delfinv2.Model.Categoria;
import com.example.delfinv2.R;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterCategorias extends RecyclerView.Adapter<AdapterCategorias.ViewHolderDatos> implements View.OnClickListener {

    private ArrayList<Categoria> ListCategorias;
    private Context mContext;
    private View.OnClickListener listener;

    public AdapterCategorias(ArrayList<Categoria> listCategorias, Context mContext) {
        ListCategorias = listCategorias;
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_questioncat,null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.catQuestionCardView.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_in));
        switch (Locale.getDefault().getLanguage()){
            case "es":
                holder.catQuestionTextView.setText(ListCategorias.get(position).getNombres_es().toString());
                break;
            case "en":
                holder.catQuestionTextView.setText(ListCategorias.get(position).getNombres_en().toString());
                break;
            case "fr":
                holder.catQuestionTextView.setText(ListCategorias.get(position).getNombres_fr().toString());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return ListCategorias.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        private TextView catQuestionTextView;
        private CardView catQuestionCardView;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            catQuestionTextView = itemView.findViewById(R.id.catQuestionTextView);
            catQuestionCardView = itemView.findViewById(R.id.catQuestionCardView);
        }

    }
}

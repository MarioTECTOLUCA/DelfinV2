package com.example.delfinv2.ui.Calendario;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.delfinv2.Adapter.eventosAdapter;
import com.example.delfinv2.Model.Eventos;
import com.example.delfinv2.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


public class CalendarioFragment extends Fragment implements View.OnClickListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private View root;
    private ConstraintLayout expandViewCat,expandViewStat,expandViewType;
    private CardView cardcat,cardstat,cardtype;
    private TextView arrowCat,arrowStat,arrowType;
    private EditText filterDate;
    private RecyclerView recyclerView;
    private ArrayList<Eventos> eventosArrayList;
    private RequestQueue requestQueue;
    private JsonObjectRequest objectRequest;
    private Button btnAply, btnErase;
    private CheckBox CBCatW,CBCatE,CBCatO,CBCatC,CBCatS,CBStatA,CBStatP,CBStatPss,CBStatC,CBTypeP,CBTypeV;
    private String url;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_calendario, container, false);
        initComponents();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initComponents(){
        url = getString(R.string.baseurl)+"events.php?";

        expandViewCat = root.findViewById(R.id.expandedViewCat);
        expandViewStat = root.findViewById(R.id.expandedViewStat);
        expandViewType = root.findViewById(R.id.expandedViewType);
        cardcat = root.findViewById(R.id.cardViewExpandCat);
        cardcat.setOnClickListener(this);
        cardstat = root.findViewById(R.id.cardViewExpandStat);
        cardstat.setOnClickListener(this);
        cardtype = root.findViewById(R.id.cardViewExpandType);
        cardtype.setOnClickListener(this);
        arrowCat = root.findViewById(R.id.textarrowCat);
        arrowStat = root.findViewById(R.id.textarrowStat);
        arrowType = root.findViewById(R.id.textarrowType);
        filterDate = root.findViewById(R.id.filterEditTextDate);
        filterDate.setOnClickListener(this);

        btnAply = root.findViewById(R.id.filterBtnApply);
        btnAply.setOnClickListener(this);
        btnErase = root.findViewById(R.id.filterBtnErase);
        btnErase.setOnClickListener(this);

        CBCatW = root.findViewById(R.id.check_catevent_taller);
        CBCatE = root.findViewById(R.id.check_catevent_expo);
        CBCatO = root.findViewById(R.id.check_catevent_oficial);
        CBCatC = root.findViewById(R.id.check_catevent_conf);
        CBCatS = root.findViewById(R.id.check_catevent_sport);

        CBStatA = root.findViewById(R.id.check_statevent_act);
        CBStatP = root.findViewById(R.id.check_statevent_pend);
        CBStatPss = root.findViewById(R.id.check_statevent_pass);
        CBStatC = root.findViewById(R.id.check_statevent_canc);

        CBTypeP = root.findViewById(R.id.check_typeevent_pres);
        CBTypeV = root.findViewById(R.id.check_typeevent_virt);

        eventosArrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        recyclerView = root.findViewById(R.id.recyclerEventsList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        loadWS();

    }

    private String CheckFilters(){
        String filters = "cat=";
        ArrayList<String> cat;
        ArrayList<String> stat;
        ArrayList<String> type;
        if (CBCatW.isChecked() || CBCatE.isChecked() || CBCatO.isChecked() || CBCatC.isChecked() || CBCatS.isChecked()){
            cat = new ArrayList<>();
            if (CBCatW.isChecked()){
                cat.add("1");
            }
            if (CBCatE.isChecked()){
                cat.add("2");
            }
            if (CBCatO.isChecked()){
                cat.add("3");
            }
            if (CBCatC.isChecked()){
                cat.add("4");
            }
            if (CBCatS.isChecked()){
                cat.add("5");
            }
            filters += cat.toString().replace("[","").replace("]","").replace(" ","")+"&";
        }else{
            filters+="X&";
        }
        if(CBStatA.isChecked() || CBStatP.isChecked() || CBStatPss.isChecked() || CBStatC.isChecked()){
            stat = new ArrayList<>();
            if (CBStatA.isChecked()){
                stat.add("ACTIVO");
            }
            if (CBStatP.isChecked()){
                stat.add("PENDIENTE");
            }
            if (CBStatPss.isChecked()){
                stat.add("PASADO");
            }
            if (CBStatC.isChecked()){
                stat.add("CANCELADA");
            }
            filters += "stat="+stat.toString().replace("[","").replace("]","").replace(" ","")+"&";
        }else{
            filters+="stat=X&";
        }
        if (CBTypeP.isChecked() || CBTypeV.isChecked()){
            type = new ArrayList<>();
            if (CBTypeP.isChecked()){
                type.add("1");
            }
            if (CBTypeV.isChecked()){
                type.add("2");
            }
            filters += "type="+type.toString().replace("[","").replace("]","").replace(" ","")+"&";
        }else{
            filters+="type=X&";
        }
        if (!filterDate.getText().toString().isEmpty()){
            filters += "day="+filterDate.getText().toString().replace("/","_");
        }else{
            filters += "day=0";
        }
        return url+filters;
    }

    private void loadWS() {
        objectRequest = new JsonObjectRequest(Request.Method.GET,CheckFilters(),null,this,this);
        requestQueue.add(objectRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardViewExpandCat:
                setVisibility(cardcat, expandViewCat, arrowCat);
                setToGone(cardstat, expandViewStat, arrowStat);
                setToGone(cardtype, expandViewType, arrowType);
                break;
            case R.id.cardViewExpandStat:
                setVisibility(cardstat, expandViewStat, arrowStat);
                setToGone(cardcat, expandViewCat, arrowCat);
                setToGone(cardtype, expandViewType, arrowType);
                break;
            case R.id.cardViewExpandType:
                setVisibility(cardtype, expandViewType, arrowType);
                setToGone(cardcat, expandViewCat, arrowCat);
                setToGone(cardstat, expandViewStat, arrowStat);
                break;
            case R.id.filterEditTextDate:
                setToGone(cardtype, expandViewType, arrowType);
                setToGone(cardcat, expandViewCat, arrowCat);
                setToGone(cardstat, expandViewStat, arrowStat);
                showPickerDialog();
                break;
            case R.id.filterBtnApply:
                setToGone(cardtype, expandViewType, arrowType);
                setToGone(cardcat, expandViewCat, arrowCat);
                setToGone(cardstat, expandViewStat, arrowStat);
                loadWS();
                break;
        }
    }

    private void setVisibility(CardView card, ConstraintLayout toexpand, TextView arrow){
        if (toexpand.getVisibility() == View.GONE){
            TransitionManager.beginDelayedTransition(card, new AutoTransition());
            toexpand.setVisibility(View.VISIBLE);
            arrow.setBackgroundResource(R.drawable.ic_arrow_up);
        }else{
            TransitionManager.beginDelayedTransition(card, new AutoTransition());
            toexpand.setVisibility(View.GONE);
            arrow.setBackgroundResource(R.drawable.ic_arrow_down);
        }
    }

    private void setToGone(CardView card, ConstraintLayout toexpand, TextView arrow){
        TransitionManager.beginDelayedTransition(card, new AutoTransition());
        toexpand.setVisibility(View.GONE);
        arrow.setBackgroundResource(R.drawable.ic_arrow_down);
    }

    private void showPickerDialog(){
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String fecha = String.valueOf(dayOfMonth) +"/"+String.valueOf(monthOfYear)+"/"+String.valueOf(year);
                filterDate.setText(fecha);
            }
        }, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
        datePicker.show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Eventos evento = null;
        eventosArrayList.clear();
        JSONArray jsonArray = response.optJSONArray("event");
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                evento = new Eventos();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                evento.setId(jsonObject.optInt("id"));
                evento.setNombre_es(jsonObject.optString("nombre_es"));
                evento.setNombre_en(jsonObject.optString("nombre_en"));
                evento.setNombre_fr(jsonObject.optString("nombre_fr"));
                evento.setDescripcion_es(jsonObject.getString("descripcion_es"));
                evento.setDescripcion_en(jsonObject.getString("descripcion_en"));
                evento.setDescripcion_fr(jsonObject.getString("descripcion_fr"));
                evento.setRequisitos_en(jsonObject.optString("requisitos_en"));
                evento.setRequisitos_es(jsonObject.optString("requisitos_es"));
                evento.setRequisitos_fr(jsonObject.optString("requisitos_fr"));
                evento.setFecha(jsonObject.optString("fecha"));
                evento.setHora_inicio(jsonObject.optString("hora_inicio"));
                evento.setHora_fin(jsonObject.optString("hora_fin"));
                evento.setCupo(jsonObject.optString("cupo"));
                evento.setStatus(jsonObject.optString("status"));
                evento.setImagen(jsonObject.optString("imagen"));
                evento.setFk_tipo(jsonObject.optInt("fk_tipo"));
                evento.setFk_cat(jsonObject.optInt("fk_cat"));
                evento.setTipo_es(jsonObject.optString("tipo_es"));
                evento.setTipo_en(jsonObject.optString("tipo_en"));
                evento.setTipo_fr(jsonObject.optString("tipo_fr"));
                evento.setCat_en(jsonObject.optString("cat_en"));
                evento.setCat_es(jsonObject.optString("cat_es"));
                evento.setCat_fr(jsonObject.optString("cat_fr"));
                evento.setFk_sala_id(jsonObject.optInt("fk_sala_id"));
                evento.setFk_ubi(jsonObject.optInt("fk_ubi"));
                evento.setFk_ubi_name(jsonObject.optString("fk_ubi_name"));
                evento.setFk_sala_virt(jsonObject.optString("fk_sala_virt"));
                evento.setFk_sala_virt_id(jsonObject.optInt("fk_sala_virt_id"));
                evento.setLink(jsonObject.optString("link"));
                evento.setFk_ponente(jsonObject.optString("fk_ponente"));
                eventosArrayList.add(evento);
            }
            eventosAdapter adapter = new eventosAdapter(eventosArrayList,getContext());
            recyclerView.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(getContext(),"Error: exception",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
    }
}
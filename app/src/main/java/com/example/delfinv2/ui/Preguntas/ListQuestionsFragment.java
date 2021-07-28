package com.example.delfinv2.ui.Preguntas;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.delfinv2.Adapter.AdapterPreguntas;
import com.example.delfinv2.Model.Pregunta;
import com.example.delfinv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class ListQuestionsFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{


    private int id;
    private View root;
    private RequestQueue request;
    private JsonObjectRequest JOR;
    private String url;
    private ArrayList<Pregunta> Listpreguntas;
    private AdapterPreguntas adapter;
    private RecyclerView recyclerPreguntas;
    private Dialog myDialog;
    private TextView txtclose,Preguntapop,respuestapop;
    private ConstraintLayout RespuestaLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_list_questions, container, false);
        Bundle arguments = getArguments();
        if (arguments != null){
            id = arguments.getInt("id");
        }else{
            Toast.makeText(getContext(),"No se recibio nada",Toast.LENGTH_LONG).show();}
        InitComponents();
        CargarWS();
        return root;
    }

    private void CargarWS() {
        url = getString(R.string.baseurl)+"listaPreguntas.php?id="+id;
        JOR = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(JOR);
    }

    private void InitComponents() {
        recyclerPreguntas = root.findViewById(R.id.RecyclerListQuestions);
        recyclerPreguntas.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerPreguntas.setHasFixedSize(true);
        Listpreguntas = new ArrayList<>();
        request = Volley.newRequestQueue(getContext());
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.popquestion);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Parece que aun no hay preguntas",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Pregunta c = null;

        JSONArray json = response.optJSONArray("preguntas");
        try {
            for (int i = 0; i < json.length(); i++) {
                JSONObject JO = json.getJSONObject(i);
                c = new Pregunta();
                c.setId(JO.getInt("id"));
                c.setPregunta_es(JO.getString("pregunta_es"));
                c.setPregunta_en(JO.getString("pregunta_en"));
                c.setPregunta_fr(JO.getString("pregunta_fr"));
                c.setRespuesta_es(JO.getString("respuesta_es"));
                c.setRespuesta_en(JO.getString("respuesta_en"));
                c.setRespuesta_fr(JO.getString("respuesta_fr"));
                Listpreguntas.add(c);
            }
            adapter = new AdapterPreguntas(Listpreguntas,getContext());
            recyclerPreguntas.setAdapter(adapter);
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String p = "",r = "";
                    switch (Locale.getDefault().getLanguage()){
                        case "es":
                            p = Listpreguntas.get(recyclerPreguntas.getChildAdapterPosition(v)).getPregunta_es();
                            r = Listpreguntas.get(recyclerPreguntas.getChildAdapterPosition(v)).getRespuesta_es();
                            break;
                        case "en":
                            p = Listpreguntas.get(recyclerPreguntas.getChildAdapterPosition(v)).getPregunta_en();
                            r = Listpreguntas.get(recyclerPreguntas.getChildAdapterPosition(v)).getRespuesta_en();
                            break;
                        case "fr":
                            p = Listpreguntas.get(recyclerPreguntas.getChildAdapterPosition(v)).getPregunta_fr();
                            r = Listpreguntas.get(recyclerPreguntas.getChildAdapterPosition(v)).getRespuesta_fr();
                            break;
                    }
                    ShowPopup(v,p,r);
                }
            });
        } catch (JSONException ex) {
            ex.printStackTrace();
            Toast.makeText(getContext(),"No se puede conectar a la red", Toast.LENGTH_LONG).show();
        }
    }

    private void ShowPopup(View v,String Pregunta,String Respuesta) {

        txtclose = (TextView) myDialog.findViewById(R.id.closepop);
        Preguntapop = (TextView) myDialog.findViewById(R.id.Preguntapop);
        respuestapop = (TextView) myDialog.findViewById(R.id.respuestapop);
        RespuestaLayout = myDialog.findViewById(R.id.respuestalayout);
        RespuestaLayout.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_in));


        Preguntapop.setText(Pregunta);
        respuestapop.setText(Respuesta);


        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}

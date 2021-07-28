package com.example.delfinv2.ui.Preguntas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.delfinv2.Adapter.AdapterCategorias;
import com.example.delfinv2.Adapter.IComunica;
import com.example.delfinv2.Model.Categoria;
import com.example.delfinv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PreguntasFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    private View root;
    private RequestQueue request;
    private JsonObjectRequest JOR;
    private String url;
    private ArrayList<Categoria> Listcategorias;
    private AdapterCategorias adapter;
    private RecyclerView recyclerCategorias;
    private Activity activity;
    private IComunica interfaceComunica;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_preguntas, container, false);
        InitComponents();
        CargarWS();
        return root;
    }

    private void InitComponents() {
        recyclerCategorias = root.findViewById(R.id.RecyclerCategorias);
        recyclerCategorias.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerCategorias.setHasFixedSize(true);
        Listcategorias = new ArrayList<>();
        request = Volley.newRequestQueue(getContext());
    }

    private void CargarWS(){
        url = getString(R.string.baseurl)+"ListaCategorias.php";
        JOR = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(JOR);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Error: "+error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.activity = (Activity) context;
            interfaceComunica = (IComunica) this.activity;
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        Categoria c = null;

        JSONArray json = response.optJSONArray("categorias");
        try {

            for (int i = 0; i < json.length(); i++) {
                JSONObject JO = json.getJSONObject(i);
                c = new Categoria();
                c.setId(JO.getInt("id"));
                c.setNombres_es(JO.getString("nombre_es"));
                c.setNombres_en(JO.getString("nombre_en"));
                c.setNombres_fr(JO.getString("nombre_fr"));
                Listcategorias.add(c);
            }
            adapter = new AdapterCategorias(Listcategorias,getContext());
            recyclerCategorias.setAdapter(adapter);
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    interfaceComunica.enviarid(Listcategorias.get(recyclerCategorias.getChildAdapterPosition(v)).getId());
                }
            });
        } catch (JSONException ex) {
            ex.printStackTrace();
            Toast.makeText(getContext(),"Error", Toast.LENGTH_LONG).show();
        }
    }
}
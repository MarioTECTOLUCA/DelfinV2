package com.example.delfinv2.ui.Schedule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.delfinv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScheduleFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    private EditText search;
    private TextView id,nombre,fecha,bloque,salon,ubi,sede;
    private Button consult;

    private RequestQueue r;
    private JsonObjectRequest JOR;

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_schedule, container, false);
        initComponents();

        consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarWebService();
            }
        });
        return root;
    }

    private void initComponents(){
        search = root.findViewById(R.id.searchField);
        id = root.findViewById(R.id.idPon);
        nombre = root.findViewById(R.id.nombrePon);
        fecha = root.findViewById(R.id.fechaPon);
        bloque = root.findViewById(R.id.bloquePon);
        salon = root.findViewById(R.id.salonPon);
        ubi = root.findViewById(R.id.ubiPon);
        sede = root.findViewById(R.id.sedePon);
        consult = root.findViewById(R.id.btnConsult);
        r = Volley.newRequestQueue(getContext());
    }

    private void CargarWebService() {
        String url = getString(R.string.baseurl)+"searchPonente.php?search="+search.getText().toString();
        JOR = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        r.add(JOR);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),getString(R.string.scheduleErrorResponse),Toast.LENGTH_LONG);
        delFields();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = response.optJSONArray("ponente");
        try {
            JSONObject JO = json.getJSONObject(0);
            if (JO.getString("id")!="0"){
                id.setText(JO.getString("idpon"));
                nombre.setText(JO.getString("nombre"));
                fecha.setText(JO.getString("fecha"));
                bloque.setText(JO.getString("bloque"));
                salon.setText(JO.getString("salon"));
                ubi.setText(JO.getString("ubicacion"));
                sede.setText(JO.getString("sede"));
            }else{
                delFields();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void delFields(){
        id.setText("");
        nombre.setText("Sin coincidencias");
        fecha.setText("");
        bloque.setText("");
        salon.setText("");
        ubi.setText("");
        sede.setText("");
    }
}
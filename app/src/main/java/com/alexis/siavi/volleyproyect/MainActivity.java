package com.alexis.siavi.volleyproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexis.siavi.volleyproyect.model.Country;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private RequestQueue requestQueue;
    Button btnListar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.tv1);
        requestQueue=Volley.newRequestQueue(this);
        btnListar=findViewById(R.id.button3);
        //Recibir datos desde api.
        Enviar();
    }

    public void getDatos(View v){
        textView.setText("");
        String url="http://192.168.0.102:8080/api/cliente";//endpoint.
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject object=new JSONObject(response.get(i).toString());
                        textView.append("Id: "+object.getInt("id")+"\n");
                        textView.append("Nombre: "+object.getString("nombre")+"\n");
                        textView.append("Apellido: "+object.getString("apellido")+"\n");
                        textView.append("Email: "+object.getString("email")+"\n");
                        textView.append("Fecha: "+object.getString("createat")+"\n");
                        textView.append("----------------------------\n");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //MANEJAMOS EL ERROR.
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void Enviar(){
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),postCliente_activity.class);
                startActivity(intent);
            }
        });
    }


}
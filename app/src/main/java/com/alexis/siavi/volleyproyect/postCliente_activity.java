package com.alexis.siavi.volleyproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class postCliente_activity extends AppCompatActivity {

    private EditText nombre, apellido, email, createat;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_cliente);
        Intent intent = getIntent();
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        email = findViewById(R.id.txtEmail);
        createat = findViewById(R.id.txtFecha);
        requestQueue = Volley.newRequestQueue(this);
    }

    public void insertar(View v) {
        String url = "http://192.168.0.102:8080/api/cliente";
        JSONObject parametros = new JSONObject();
        try {
            parametros.put("nombre", nombre.getText().toString());
            parametros.put("apellido", apellido.getText().toString());
            parametros.put("email", email.getText().toString());
            parametros.put("createat", createat.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.POST,
                url,
                parametros,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.get("respuesta").toString().equals("ok")) {
                                Toast.makeText(postCliente_activity.this, "Los datos se guardaron correctamente", Toast.LENGTH_SHORT).show();
                                nombre.setText("");
                                apellido.setText("");
                                email.setText("");
                                createat.setText("");
                            } else
                                Toast.makeText(postCliente_activity.this, response.get("respuesta").toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(postCliente_activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(requerimiento);
    }
}
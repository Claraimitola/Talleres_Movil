package com.example.notasdocente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_principal);
    }


    public void mostrarActividadCrudUniversidades(View v) {
        Intent intento = new Intent(this, ActividadCrudUniversidad.class);
        startActivity(intento);
    }
}

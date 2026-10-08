package com.example.ejemplobasico;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;
    private TextView tvMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNombre = findViewById(R.id.etNombre);
        tvMensaje = findViewById(R.id.tvMensaje);
    }


    public void mostrarSaludo(View view) {
        String nombre = etNombre.getText().toString().trim();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Por favor escribe tu nombre", Toast.LENGTH_SHORT).show();
            tvMensaje.setText("");
        } else {
            String saludo = "¡Hola, " + nombre + "! Bienvenida a mi primera App.";
            tvMensaje.setText(saludo);
        }
    }
}
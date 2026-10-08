package com.example.convertidormoneda;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etMonto;
    private RadioButton rbUSD, rbEUR;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etMonto = findViewById(R.id.etMonto);
        rbUSD = findViewById(R.id.rbUSD);
        rbEUR = findViewById(R.id.rbEUR);
        tvResultado = findViewById(R.id.tvResultado);
    }

    // Método que se ejecuta al presionar el botón Convertir
    public void convertirMoneda(View view) {
        String textoMonto = etMonto.getText().toString();

        if (textoMonto.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un monto", Toast.LENGTH_SHORT).show();
            return;
        }

        double monto = Double.parseDouble(textoMonto);
        double resultado = 0;
        String moneda = "";


        if (rbUSD.isChecked()) {
            resultado = monto / 3200.0;
            moneda = "USD";
        } else if (rbEUR.isChecked()) {
            resultado = monto / 3600.0;
            moneda = "EUR";
        }

        tvResultado.setText(String.format("Resultado: %.2f %s", resultado, moneda));
    }
}
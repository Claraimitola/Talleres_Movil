package com.example.calculadoracredito;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etValorCredito, etCuotas, etInteres;
    private TextView tvCuotaMensual, tvValorTotal, tvGananciaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etValorCredito = findViewById(R.id.etValorCredito);
        etCuotas = findViewById(R.id.etCuotas);
        etInteres = findViewById(R.id.etInteres);

        tvCuotaMensual = findViewById(R.id.tvCuotaMensual);
        tvValorTotal = findViewById(R.id.tvValorTotal);
        tvGananciaTotal = findViewById(R.id.tvGananciaTotal);
    }

    public void calcularCredito(View view) {
        String strValor = etValorCredito.getText().toString();
        String strCuotas = etCuotas.getText().toString();
        String strInteres = etInteres.getText().toString();

        if (strValor.isEmpty() || strCuotas.isEmpty() || strInteres.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double valorCredito = Double.parseDouble(strValor);
        int numCuotas = Integer.parseInt(strCuotas);
        double porcentajeInteres = Double.parseDouble(strInteres);

        if (numCuotas <= 0) {
            Toast.makeText(this, "El número de cuotas debe ser mayor a 0", Toast.LENGTH_SHORT).show();
            return;
        }


        double gananciaTotalInteres = valorCredito * (porcentajeInteres / 100.0) * numCuotas;
        double valorTotalCredito = valorCredito + gananciaTotalInteres;
        double valorCuota = valorTotalCredito / numCuotas;


        tvCuotaMensual.setText(String.format("Valor Cuota Mensual: $%.2f", valorCuota));
        tvValorTotal.setText(String.format("Valor Total del Crédito: $%.2f", valorTotalCredito));
        tvGananciaTotal.setText(String.format("Ganancia Total (Intereses): $%.2f", gananciaTotalInteres));
    }
}
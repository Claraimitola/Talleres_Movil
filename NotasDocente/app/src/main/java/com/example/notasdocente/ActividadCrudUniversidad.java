package com.example.notasdocente;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notasdocente.dao.DaoUniversidad;
import com.example.notasdocente.entidades.Universidad;

public class ActividadCrudUniversidad extends AppCompatActivity {


    private EditText campoId;
    private EditText campoNombre;
    private EditText campoWww;
    private DaoUniversidad dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_crud_universidad);
        campoId = findViewById(R.id.campoId);
        campoNombre = findViewById(R.id.campoNombre);
        campoWww = findViewById(R.id.campoWww);
        try {
            dao = new DaoUniversidad(this, "notasdocentev1", 1);
            campoId.setText("" + dao.proximoId());
        } catch (Exception e) {
            mensaje(e.getMessage());
        }
    }

    private void mensaje(String texto) {
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
    }


    public void agregarUniversidad(View v) {
        Universidad u = new Universidad();
        u.setNombre(campoNombre.getText().toString());
        u.setWww(campoWww.getText().toString());
        try {
            dao.agregarUniversidad(u);
            mensaje("Mensaje: Universidad Agregada");
            limpiarCampos(v);
        } catch (Exception e) {
            mensaje("ERROR: " + e.getMessage());
        }
    }


    public void limpiarCampos(View v) {
        campoNombre.setText("");
        campoWww.setText("");
        try {
            campoId.setText("" + dao.proximoId());
        } catch (Exception e) {
            campoId.setText("");
        }
    }


    public void mostrarCuadroDialogoId(final View componente) {
        LayoutInflater layoutEmergente = LayoutInflater.from(this);
        View formularioId = layoutEmergente.inflate(R.layout.layout_dialog_buscar_id, null);
        final EditText campoBuscarId = formularioId.findViewById(R.id.campoBuscarId);

        AlertDialog.Builder dialogoId = new AlertDialog.Builder(this);
        dialogoId.setView(formularioId);
        dialogoId.setCancelable(false);
        dialogoId.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                campoId.setText(campoBuscarId.getText());
                buscarUniversidad(componente);
            }
        });
        dialogoId.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                limpiarCampos(componente);
            }
        });
        dialogoId.create().show();
    }

    public void buscarUniversidad(View v) {
        String id = campoId.getText().toString();
        try {
            Universidad u = dao.consultarUnaUniversidad(id);
            campoNombre.setText(u.getNombre());
            campoWww.setText(u.getWww());
            mensaje("Mensaje: Universidad Encontrada");
        } catch (Exception e) {
            mensaje("ERROR: " + e.getMessage());
            limpiarCampos(v);
        }
    }


    public void modificarUniversidad(View v) {
        String id = campoId.getText().toString().trim();
        if (id.isEmpty()) {
            mensaje("Primero busque la Universidad a modificar");
            return;
        }
        Universidad u = new Universidad();
        u.setId(id);
        u.setNombre(campoNombre.getText().toString());
        u.setWww(campoWww.getText().toString());
        try {
            dao.editarUniversidad(u);
            mensaje("Mensaje: Universidad " + id + " Modificada");
            limpiarCampos(v);
        } catch (Exception e) {
            mensaje("ERROR: " + e.getMessage());
        }
    }


    public void eliminarUniversidad(final View v) {
        final String id = campoId.getText().toString().trim();
        if (id.isEmpty()) {
            mensaje("Digite o busque el ID de la Universidad a eliminar");
            return;
        }
        AlertDialog.Builder confirmar = new AlertDialog.Builder(this);
        confirmar.setTitle("Eliminar Universidad");
        confirmar.setMessage("¿Seguro que desea eliminar la Universidad con ID " + id + "?");
        confirmar.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    dao.borrarUniversidad(id);
                    mensaje("Mensaje: Universidad " + id + " Eliminada");
                    limpiarCampos(v);
                } catch (Exception e) {
                    mensaje("ERROR: " + e.getMessage());
                }
            }
        });
        confirmar.setNegativeButton("No", null);
        confirmar.create().show();
    }


    @Override
    protected void onDestroy() {
        if (dao != null && dao.getBd() != null) {
            dao.getBd().cerrar();
        }
        super.onDestroy();
    }
}

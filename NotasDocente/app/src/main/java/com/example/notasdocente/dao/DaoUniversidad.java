package com.example.notasdocente.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.example.notasdocente.entidades.Universidad;

public class DaoUniversidad {

    private ConexionBasedatos bd;

    public DaoUniversidad() { }

    public DaoUniversidad(Context actividad, String nombre, int version) throws Exception {
        bd = new ConexionBasedatos(actividad, nombre, null, version);
        bd.conectar(ConexionBasedatos.MODO_ESCRITURA);
    }


    public void agregarUniversidad(Universidad institucion) throws Exception {
        ContentValues args = new ContentValues();
        if (institucion.getNombre() != null && !institucion.getNombre().trim().isEmpty()) {
            args.put("nombre", institucion.getNombre().trim());
        } else {
            throw new Exception("El nombre de la Universidad es Requerido");
        }
        if (institucion.getWww() != null && !institucion.getWww().trim().isEmpty()) {
            args.put("www", institucion.getWww().trim());
        } else {
            args.putNull("www");
        }
        bd.insertar("Universidades", args);
    }


    public Universidad getUniversidad(Cursor resultado) {
        Universidad institucion = new Universidad();
        institucion.setId(resultado.getString(0));
        institucion.setNombre(resultado.getString(1));
        institucion.setWww(resultado.getString(2));
        return institucion;
    }


    public Universidad consultarUnaUniversidad(String id) throws Exception {
        String[] args = new String[]{id};
        Cursor resultado = bd.consultar("SELECT * FROM Universidades WHERE id=?", args);
        try {
            if (resultado.moveToFirst()) {
                return getUniversidad(resultado);
            } else {
                throw new Exception("La Universidad " + id + " no ha sido agregada");
            }
        } finally {
            resultado.close();
        }
    }


    public List<Universidad> listarTodasUniversidades() throws Exception {
        List<Universidad> lista = new ArrayList<>();
        Cursor resultado = bd.consultar("SELECT * FROM Universidades", null);
        while (resultado.moveToNext()) {
            lista.add(getUniversidad(resultado));
        }
        resultado.close();
        if (lista.isEmpty()) {
            throw new Exception("No se han agregado Universidades al sistema");
        }
        return lista;
    }


    public void borrarUniversidad(String id) throws Exception {
        consultarUnaUniversidad(id); // si no existe, lanza el error
        String sqlDelete = "DELETE FROM Universidades WHERE id = ?";
        String[] valoresWhere = {id};
        bd.eliminar(sqlDelete, valoresWhere);
    }


    public void editarUniversidad(Universidad institucion) throws Exception {
        if (institucion.getNombre() == null || institucion.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre de la Universidad es Requerido");
        }
        ContentValues columnasValor = new ContentValues();
        columnasValor.put("nombre", institucion.getNombre().trim());
        columnasValor.put("www", institucion.getWww());
        String[] valoresWhere = {institucion.getId()};
        int cambios = bd.actualizar("Universidades", columnasValor, "id=?", valoresWhere);
        if (cambios == 0) {
            throw new Exception("La Universidad " + institucion.getId() + " no existe");
        }
    }


    public int proximoId() throws Exception {
        Cursor resultado = bd.consultar("SELECT MAX(id) FROM Universidades", null);
        try {
            if (resultado.moveToFirst()) {
                return resultado.getInt(0) + 1;
            }
            return 1;
        } finally {
            resultado.close();
        }
    }

    public ConexionBasedatos getBd() {
        return bd;
    }
}
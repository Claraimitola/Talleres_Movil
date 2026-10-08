package com.example.notasdocente.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionBasedatos extends SQLiteOpenHelper {


    private SQLiteDatabase conexion;
    private String nombreBD;
    private int versionBD;
    private Context actividad;
    private String estado = "";
    public final static int MODO_LECTURA = 0;
    public final static int MODO_ESCRITURA = 1;


    public ConexionBasedatos(Context actividad, String nombreBD,
                             CursorFactory cursor, int versionBD) {
        super(actividad, nombreBD, cursor, versionBD);
        this.nombreBD = nombreBD;
        this.versionBD = versionBD;
        this.actividad = actividad;
        estado = "Instancia SI\n";
    }


    @Override
    public void onCreate(SQLiteDatabase bd) {
        try {
            for (String sentencia : CodigoSQLBackUp.sqlBackup.split(";")) {
                if (!sentencia.trim().isEmpty()) {
                    bd.execSQL(sentencia);
                }
            }
            estado += "onCreate SI\n";
        } catch (SQLiteException e) {
            estado += "onCreate NO\n";
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionActual, int versionNueva) {
        bd.execSQL("DROP TABLE IF EXISTS Calificaciones");
        bd.execSQL("DROP TABLE IF EXISTS Asignaturas");
        bd.execSQL("DROP TABLE IF EXISTS Alumnos");
        bd.execSQL("DROP TABLE IF EXISTS Programas");
        bd.execSQL("DROP TABLE IF EXISTS Universidades");
        onCreate(bd);
    }


    public void conectar(int modo) throws Exception {
        try {
            if (modo == MODO_ESCRITURA) {
                conexion = this.getWritableDatabase();
                estado += "Conexion Write SI\n";
            } else {
                conexion = this.getReadableDatabase();
                estado += "Conexion Read SI\n";
            }
        } catch (SQLiteException error) {
            estado += "Conexion Read/Write NO\n";
            throw new Exception("ERROR: Sin conexion a la BD\nMensaje:" + error.getMessage());
        }
    }


    public void insertar(String tabla, ContentValues columnasValor) throws Exception {
        try {
            conexion.insertOrThrow(tabla, null, columnasValor);
        } catch (SQLException e) {
            throw new Exception("Mensaje:" + e.getMessage());
        }
    }


    public int actualizar(String tabla, ContentValues columnasValor,
                          String whereColumnasIgualValor, String[] valoresWhere) throws Exception {
        try {
            return conexion.update(tabla, columnasValor, whereColumnasIgualValor, valoresWhere);
        } catch (SQLException e) {
            throw new Exception("Mensaje:" + e.getMessage());
        }
    }


    public void eliminar(String sqlDelete, String[] valoresWhere) throws Exception {
        try {
            if (valoresWhere == null) {
                conexion.execSQL(sqlDelete);
            } else {
                conexion.execSQL(sqlDelete, valoresWhere);
            }
        } catch (SQLException e) {
            throw new Exception("Mensaje:" + e.getMessage());
        }
    }


    public Cursor consultar(String consultaSQL, String[] valoresWhere) throws Exception {
        try {
            return conexion.rawQuery(consultaSQL, valoresWhere);
        } catch (SQLException e) {
            throw new Exception("Mensaje:" + e.getMessage());
        }
    }


    public void cerrar() {
        if (conexion != null && conexion.isOpen()) {
            conexion.close();
        }
    }

    public String getEstado() {
        return estado;
    }
}
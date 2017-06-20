package com.krivic.mismascotasc4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.krivic.mismascotasc4.pojo.Mascota;

import java.util.ArrayList;



public class BaseDatos extends SQLiteOpenHelper{

    private Context context;
    public BaseDatos(Context context) {
        super(context, com.krivic.mismascotasc4.db.ConstantesBaseDatos.DATABASE_NAME, null, com.krivic.mismascotasc4.db.ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE    + " TEXT, " +
                com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS_FOTO      + " INTEGER" +
                ")";
        Toast.makeText(context, "queryCrearTablamascota", Toast.LENGTH_SHORT).show();

        String queryCrearTablaLikesMascota = "CREATE TABLE " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS + "(" +
                com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + " INTEGER, " +
                com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + ") " +
                "REFERENCES " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS + "(" + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")" +
                ")";

        String queryCrearTablaUserInstagram = "CREATE TABLE " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_USER_INSTAGRAM + "(" +
                com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_USER_INSTAGRAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_USER_INSTAGRAM_NOMBRE + " TEXT " +
                ")";


        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
        db.execSQL(queryCrearTablaUserInstagram);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXIST" + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS);
        db.execSQL("DROP TABLE IF EXIST" + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_USER_INSTAGRAM);
        onCreate(db);
    }
    public ArrayList<Mascota> obtenerTodasLasMascotas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query ="SELECT * FROM " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryLikes =  "SELECT COUNT(" + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + ") as likes " +
                    " FROM " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS +
                    " WHERE " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + "=" + mascotaActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                mascotaActual.setRaiting(registrosLikes.getInt(0));
            }else {
                mascotaActual.setRaiting(0);
            }

            mascotas.add(mascotaActual);
        }
        db.close();
        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;
        String query =  "SELECT COUNT(" + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + ")" +
                " FROM " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS +
                " WHERE " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + "=" + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();
        return likes;
    }

    public ArrayList<Mascota> obtenerCincoMascotasFavoritas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();
       // String queryMascotasFavoritas ="SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        String queryMascotasFavoritas =  "SELECT " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS_ID + ", " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + ", " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS_FOTO + ", "
                         +  com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + ", " + "SUM("+ com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES+")"  +
                        " FROM " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS + " INNER JOIN "
                     + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS
                      + " ON " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_MASCOTAS_ID + "=" + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA
                       + " GROUP BY " + com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA
                      +   " ORDER BY SUM("+ com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + ") DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(queryMascotasFavoritas, null);

        int i = 0;
        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));
            mascotaActual.setRaiting(registros.getInt(4));
     //

            mascotas.add(mascotaActual);
            i = i + 1;
            if (i == 5) {
                while (registros.moveToNext()){}
            }
        }
        db.close();
        return mascotas;
    }

    public void insertarUserInstagram(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(com.krivic.mismascotasc4.db.ConstantesBaseDatos.TABLE_USER_INSTAGRAM, null, contentValues);
        db.close();
    }

}

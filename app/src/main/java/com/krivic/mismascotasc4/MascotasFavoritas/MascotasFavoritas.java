package com.krivic.mismascotasc4.MascotasFavoritas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.krivic.mismascotasc4.R;
import com.krivic.mismascotasc4.adapter.MascotaAdaptadorFavoritas;
import com.krivic.mismascotasc4.db.ConstructorMascotas;
import com.krivic.mismascotasc4.pojo.Mascota;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity {
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private ConstructorMascotas constructorMascotas;

    public MascotaAdaptadorFavoritas adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBarfav);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotasfav);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();

    }
    private void inicializarListaMascotas() {
        constructorMascotas = new ConstructorMascotas(this);
        mascotas = constructorMascotas.obtenerDatosFavoritas();
    }
        public void inicializarAdaptador() {
            adaptador = new MascotaAdaptadorFavoritas(mascotas, this);
            listaMascotas.setAdapter(adaptador);
        }
}

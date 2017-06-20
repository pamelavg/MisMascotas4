package com.krivic.mismascotasc4.Instagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.krivic.mismascotasc4.InstagramPresenter.ITimeLinePresenter;
import com.krivic.mismascotasc4.InstagramPresenter.TimeLinePresenter;
import com.krivic.mismascotasc4.R;
import com.krivic.mismascotasc4.adapter.MascotaAdaptadorTimeline;
import com.krivic.mismascotasc4.db.ConstructorMascotas;
import com.krivic.mismascotasc4.pojo.MascotaInstagram;

import java.util.ArrayList;

public class TimeLineActivity extends AppCompatActivity implements ITimeLineView {

    private ImageView ivImagenPerfilInst;
    private TextView tvNombreMascotaInst;

    ArrayList<MascotaInstagram> mascotasInstagram;
    private RecyclerView listaMascotas;
    private ITimeLinePresenter presenter;

    private ConstructorMascotas constructorMascotas;

    public TimeLineActivity() {
    }


    //mascotas favoritas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBarfav);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotas = (RecyclerView) findViewById(R.id.rvTimeLineInst);
        presenter = new TimeLinePresenter(this);

    }


    @Override
    public void generalLinearLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

    }

    @Override
    public MascotaAdaptadorTimeline crearAdaptador(ArrayList<MascotaInstagram> mascotasInstagrams) {
        MascotaAdaptadorTimeline adaptador = new MascotaAdaptadorTimeline(mascotasInstagrams, this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptadorTimeline adaptador) {
        listaMascotas.setAdapter(adaptador);

    }
}

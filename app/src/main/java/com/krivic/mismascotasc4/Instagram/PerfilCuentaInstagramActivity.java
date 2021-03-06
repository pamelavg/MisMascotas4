package com.krivic.mismascotasc4.Instagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.krivic.mismascotasc4.InstagramPresenter.IPerfilCuentaInstagramActivityPresentador;
import com.krivic.mismascotasc4.InstagramPresenter.PerfilCuentaInstagramActivityPresenter;
import com.krivic.mismascotasc4.R;
import com.krivic.mismascotasc4.adapter.MascotaAdaptadorPerfilInstagram;
import com.krivic.mismascotasc4.pojo.MascotaInstagram;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PerfilCuentaInstagramActivity extends AppCompatActivity implements IPerfilCuentaInstagramActivityView {

    private ImageView ivImagenPerfilInst;
    private TextView tvNombreMascotaInst;

    ArrayList<MascotaInstagram> mascotasInstagrams;
    private RecyclerView listaMascotas;
    private IPerfilCuentaInstagramActivityPresentador presenter;

    public PerfilCuentaInstagramActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cuenta_instagram);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        //navegacion regreso padre
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle parametros = getIntent().getExtras();

     //   MainActivity.USERNAME = parametros.getString(getResources().getString(R.string.pUsername));
      //  MainActivity.IDUSERNAME = parametros.getString(getResources().getString(R.string.pIdUsername));

        ivImagenPerfilInst = (ImageView) findViewById(R.id.ivImagenPerfilInst);
        tvNombreMascotaInst = (TextView) findViewById(R.id.tvNombreMascotaInst);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        presenter = new PerfilCuentaInstagramActivityPresenter(this);
    }
     @Override
    public void generalGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(this,3);
        listaMascotas.setLayoutManager(glm);
    }
    @Override
    public MascotaAdaptadorPerfilInstagram crearAdaptador(ArrayList<MascotaInstagram> mascotasInstagrams) {

        MascotaAdaptadorPerfilInstagram adaptador = new MascotaAdaptadorPerfilInstagram(mascotasInstagrams, this);
        return adaptador;
    }
    @Override
    public void inicializarAdaptadorRV(MascotaAdaptadorPerfilInstagram adaptadorPerfil) {
        listaMascotas.setAdapter(adaptadorPerfil);
    }

    @Override
    public void llenaEncabezado(ArrayList<MascotaInstagram> mascotasInstagrams) {
        tvNombreMascotaInst.setText(mascotasInstagrams.get(0).getUsername());

        Picasso.with(this)
                .load(mascotasInstagrams.get(0).getUrlfotoperfil())
                .placeholder(R.drawable.bolita)
                .into(ivImagenPerfilInst);


    }
}

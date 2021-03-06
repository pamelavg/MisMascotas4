package com.krivic.mismascotasc4.Instagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.krivic.mismascotasc4.MainActivity;
import com.krivic.mismascotasc4.R;
import com.krivic.mismascotasc4.db.ConstructorUserInstagram;
import com.krivic.mismascotasc4.pojo.UserInstagram;

public class ConfigurarCueInstagramActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText tiUsuario;
    private Button btnGuardarCuenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cue_instagram);

         // action bar
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        //navegacion regreso padre
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tiUsuario = (TextInputEditText) findViewById(R.id.et_Usuario);
        btnGuardarCuenta = (Button) findViewById(R.id.btn_GuardarCuenta);
        btnGuardarCuenta.setOnClickListener(this);
    }

        @Override
        public void onClick (View v){
            int viewId = v.getId();
            switch (viewId) {
                case R.id.btn_GuardarCuenta:
//                    GuardarCuenta();

                    Intent intentPerfilInstagram = new Intent(ConfigurarCueInstagramActivity.this, com.krivic.mismascotasc4.Instagram.PerfilCuentaInstagramActivity.class);
                    intentPerfilInstagram.putExtra(getResources().getString(R.string.pUsername), tiUsuario.getText().toString());
                    intentPerfilInstagram.putExtra(getResources().getString(R.string.pIdUsername), "5538249605");
                    MainActivity.USERNAME=tiUsuario.getText().toString();
                    startActivity(intentPerfilInstagram);
                    break;
            }
        }

    public void GuardarCuenta () {

         Toast.makeText(this, "Guardar cuenta: " + tiUsuario.getText() , Toast.LENGTH_SHORT).show();

        UserInstagram userInstagram = new UserInstagram();
        userInstagram.setUsername(tiUsuario.getText().toString());
        ConstructorUserInstagram constructorUserInstagram = new ConstructorUserInstagram(this);
        constructorUserInstagram.guardarCuentaUser(userInstagram);

        //nombre_usuario = constructorUserInstagram.obtenerLikesMascota(UserInstagram);
       // Toast.makeText(this, "Guardar cuenta: " + tiUsuario.getText() , Toast.LENGTH_SHORT).show();
    }
}






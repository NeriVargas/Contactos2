package com.example.contactos2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityContactos extends AppCompatActivity {

    private TextView textViewNumero;
    private TextView textViewNom;
    private TextView textViewEdad;
    private TextView textViewDescripcion;
    private ImageButton imageButtonLlamar;
    private final int PHONE_CALL_CODE = 100;
    private final int CAMERA_CALL__CODE = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);


        imageButtonLlamar = (ImageButton) findViewById(R.id.btnLlamar);

        textViewNumero = (TextView) findViewById(R.id.txtNum);
        String valor1 = getIntent().getStringExtra("Contacto");
        textViewNumero.setText(valor1);


        textViewNom = (TextView) findViewById(R.id.txtNom);
        String valor2 = getIntent().getStringExtra("Nombre");
        textViewNom.setText(valor2);

        textViewEdad = (TextView) findViewById(R.id.txtEdad);
        String valor3 = getIntent().getStringExtra("Edad");
        textViewEdad.setText(valor3);

        textViewDescripcion = (TextView) findViewById(R.id.txtDescrip);
        String valor4 = getIntent().getStringExtra("Descripcion");
        textViewDescripcion.setText(valor4);


        imageButtonLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = textViewNumero.getText().toString();
                if (num != null) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        versionesAnteriores(num);
                    }
                }


            }

            private void versionesAnteriores(String num) {
                Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel" + num));
                if (verificarPermiso(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentLlamada);
                } else {
                    Toast.makeText(ActivityContactos.this, "configura los permisos", Toast.LENGTH_SHORT).show();
                }
            }

        });
        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            //-- entra a cel
            switch (requestCode) {
                case PHONE_CALL_CODE:
                    String permission = permissions[0];
                    int result = grantResults[0];
                    if (permission.equals(Manifest.permission.CALL_PHONE)) {
                        ;

                        if (result == PackageManager.PERMISSION_GRANTED) {

                            String phoneNumber = textViewNumero.getText().toString();
                            Intent llamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                                return;
                        } else {

                            Toast.makeText(this, "No aceptaste el permiso", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                    break;
            }

        }

        private boolean verificarPermiso (String permiso){
            int resultado = this.checkCallingOrSelfPermission(permiso);
            return resultado == PackageManager.PERMISSION_GRANTED;
        }
    }
}











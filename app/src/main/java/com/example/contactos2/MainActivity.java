package com.example.contactos2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText editTextNombre;
    private EditText editTextDescripcion;
    private EditText editTextEdad;
    private EditText editTextNumero;
    private ImageButton btnTelefono;
    private Button btnBuscar;

    private final int PHONE_CALL_CODE = 100;
    private final int CAMERA_CALL__CODE = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombre = (EditText) findViewById(R.id.txt_nombre);
        editTextNumero = (EditText) findViewById(R.id.txt_telefono);
        editTextEdad = (EditText) findViewById(R.id.editTextEdad);
        editTextDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = editTextNumero.getText().toString();
                String nom = editTextNombre.getText().toString();
                String ed = editTextEdad.getText().toString();
                String desc = editTextDescripcion.getText().toString();
                Intent i = new Intent(v.getContext(), ActivityContactos.class);
                i.putExtra("Contacto", num);
                i.putExtra("Nombre", nom);
                i.putExtra("Edad", ed);
                i.putExtra("Descripcion", desc);


                startActivity(i);
                ;


            }

        });


    }


    public void Guardar(View view) {
        String nombre = editTextNombre.getText().toString();
        String telefono = editTextNumero.getText().toString();
        String edad = editTextEdad.getText().toString();
        String descrpcioj = editTextDescripcion.getText().toString();

        SharedPreferences preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias.edit();
        obj_editor.putString(nombre, telefono);
        obj_editor.putString(edad, descrpcioj);
        obj_editor.commit();

        Toast.makeText(this, "El contacto ha sido guardado", Toast.LENGTH_SHORT).show();

    }
}


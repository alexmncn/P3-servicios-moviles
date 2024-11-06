package com.alexmncn.practica3;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Localizacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacion);

        TextView latitudTV = findViewById(R.id.textView3);
        TextView longitudTV = findViewById(R.id.textView5);
        latitudTV.setText(String.format("%s", "0.0000"));
        longitudTV.setText(String.format("%s", "0.0000"));

        Button bt1 = findViewById(R.id.button_1);

        bt1.setOnClickListener(this::reloadLocation);

    }

    public void reloadLocation (View view) {
        Random random = new Random();
        String latitud = String.format("%s", (float) (36 + random.nextFloat() * (43.5 - 36)));
        String longitud = String.format("%s", (float) (-9 + random.nextFloat() * (3 - (-9))));

        TextView latitudTV = findViewById(R.id.textView3);
        TextView longitudTV = findViewById(R.id.textView5);
        latitudTV.setText(String.format("%s", latitud));
        longitudTV.setText(String.format("%s", longitud));

        saveLocation(latitud, longitud);
    }

    public void saveLocation (String latitud, String longitud) {
        String filename = "datos.txt";
        String data = latitud + ", " + longitud + "; ";

        try (FileOutputStream fos = openFileOutput(filename, MODE_APPEND)) {
            fos.write(data.getBytes());

            String fullPath = getFilesDir() + "/" + filename;
            Log.d("saveLocation", "Archivo guardado en: " + fullPath);
        } catch (IOException e) {
            Log.e(e.getMessage(), "Error al guardar el archivo");
        }

    }
}
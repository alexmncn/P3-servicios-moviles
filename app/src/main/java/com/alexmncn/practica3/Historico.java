package com.alexmncn.practica3;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Historico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        String historicLocations = loadData();

        TextView tv2 = findViewById(R.id.textView2);
        tv2.setText(historicLocations);

    }

    public String loadData() {
        String filename = "datos.txt";
        int size;
        StringBuilder data = new StringBuilder();

        try (FileInputStream fis = openFileInput(filename)) {
            while ((size = fis.read()) > 0) {
                data.append(((char) size));
            }
        } catch (IOException e) {
            Log.e(e.getMessage(), "Error al leer el archivo");
        }

        return data.toString();
    }
}
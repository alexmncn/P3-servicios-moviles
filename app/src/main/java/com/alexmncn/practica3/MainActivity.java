package com.alexmncn.practica3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_1) {
            startActivity(new Intent(this, Localizacion.class));
        } else if (itemId == R.id.menu_2) {
            startActivity(new Intent(this, Historico.class));
        } else if (itemId == R.id.menu_3) {
            startActivity(new Intent(this, Mapa.class));
        } else if (itemId == R.id.menu_4) {
            startActivity(new Intent(this, Ayuda.class));
        }

        return true;
    }
}
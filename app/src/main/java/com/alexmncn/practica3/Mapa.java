package com.alexmncn.practica3;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.alexmncn.practica3.databinding.ActivityMapaBinding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Cargar los puntos desde el archivo datos.txt
        ArrayList<LatLng> geolocalizationCoordinates = loadCoordinatesFromFile();

        // Verifica si se cargaron puntos y agrega los marcadores
        if (!geolocalizationCoordinates.isEmpty()) {
            for (LatLng localization : geolocalizationCoordinates) {
                mMap.addMarker(new MarkerOptions().position(localization).title("Ubicación"));
            }
        } else {
            Log.i("Mapa", "No se encontraron coordenadas de ubicación");
        }
    }

    // Carga las coordenadas de ubicación desde el archivo
    private ArrayList<LatLng> loadCoordinatesFromFile() {
        ArrayList<LatLng> localizationCoordinates = new ArrayList<>();

        try {
            // Abrir archivo datos.txt
            FileInputStream fis = openFileInput("datos.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String linea;

            while ((linea = reader.readLine()) != null) {
                // Separar las coordenadas
                String[] coordinates = linea.split(";");
                for (String coordinate : coordinates) {
                    // Separar Latitud y Longitud
                    String[] latLon = coordinate.split(",");
                    if (latLon.length == 2) {
                        // Crear y añadir coordenadas a la lista
                        double lat = Double.parseDouble(latLon[0].trim());
                        double lon = Double.parseDouble(latLon[1].trim());
                        localizationCoordinates.add(new LatLng(lat, lon));
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Mapa", "Error al cargar las coordenadas de ubicación: " + e.getMessage());
        }

        return localizationCoordinates;
    }
}
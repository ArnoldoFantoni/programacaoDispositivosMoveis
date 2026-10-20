package com.ifsc.app_aula1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv = findViewById(R.id.textView);
        MapView mapView = findViewById(R.id.mapView);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        org.osmdroid.config.Configuration.getInstance().serUserAgentValue(getPackageManager());

        //-- solicitar permissão em tempo de execução;
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Location l = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            tv.setText(l.getLatitude()+" - "+l.getLongitude());
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, location -> {});
            //-- inicia mapa
            mapView.getController().setZoom(18);
            mapView.getController().setCenter(
                    mew org.osmddroid.util.GeoPoint(l.getLatitude(), l.getLongitude())
            );
            mapView.getOverlay().clear();
            Marker marker = new org.osmdroid.views.overlay.Marker(mapView);
            mapView.invalidate();;

        }else{
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

    }
}
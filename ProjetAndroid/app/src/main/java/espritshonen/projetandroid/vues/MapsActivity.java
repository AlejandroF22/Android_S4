package espritshonen.projetandroid.vues;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import espritshonen.projetandroid.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double lat,lng;
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mode = getIntent().getStringExtra("mode");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mode.equals("main")) {
            String latString = getIntent().getStringExtra("lat");
            String lngString = getIntent().getStringExtra("lng");
            lat = Double.parseDouble(latString);
            lng = Double.parseDouble(lngString);
            LatLng station = new LatLng(lat, lng);
            float zoomLevel = 16f;
            mMap.addMarker(new MarkerOptions().position(station).title("Station choisie"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(station, zoomLevel));
        }else if (mode.equals("libre")){
            float zoomLevel = 13.5f;
            double[] lat = new double[0];
            double[] lng = new double[0];
            lat = getIntent().getDoubleArrayExtra("latArray");
            lng = getIntent().getDoubleArrayExtra("lngArray");
            LatLng lyon = new LatLng(45.75,4.85);
            for(int i = 0;i < lat.length;i++){
                LatLng tmp = new LatLng(lat[i],lng[i]);
                mMap.addMarker(new MarkerOptions().position(tmp));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lyon,zoomLevel));
        }


    }
}

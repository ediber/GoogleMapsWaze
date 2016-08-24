package edi.com.mymaps9;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private View navigate;
    private View waze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        navigate = findViewById(R.id.navigate);
        waze = findViewById(R.id.waze);


        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=32.058497, 34.816511&daddr=32.058497, 34.816511"));
                startActivity(intent);*/


//                http://maps.google.com/maps?saddr=[lat 1],[lon 1]&daddr=[lat 2],[lon 2]+to:[lat 3],[lon 3]+to:[lat 4],[lon 4]
                String mapUrl = "https://maps.google.com/maps?saddr=San+Francisco,+CA&daddr=Los+Angeles,+CA+to:Phoenix,+AZ+to:Houston,+TX+to:Jacksonville,+FL+to:New+York,+NY+to:Buffalo,+NY+to:Chicago,+IL+to:Seattle,+WA+to:San+Jose,+CA";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl));
                startActivity(i);
            }
        });

        waze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    String url = "waze://?q=Hawaii";
                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                    startActivity( intent );
                }
                catch ( ActivityNotFoundException ex  )
                {
                    Intent intent =
                            new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
                    startActivity(intent);
                }
            }
        });
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

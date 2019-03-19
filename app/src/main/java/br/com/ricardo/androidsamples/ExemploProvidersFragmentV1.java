package br.com.ricardo.androidsamples;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ExemploProvidersFragmentV1 extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
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
        mMap.setOnMapClickListener(this);
        mMap.getUiSettings().setZoomControlsEnabled(true);
//        mMap.setMyLocationEnabled(true);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //Na classe Criteria, você pode buscar o melhor tipo de provedor pra gerar o acesso a sua localização (GPS ou WIFI)
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Toast.makeText(getActivity(), "Provider: " + provider, Toast.LENGTH_SHORT).show();



        //Checando se o device tem permissão pra acessar o GPS
        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION }, 0);
        }



        // Coordenadas para a cidade de Sydney
        LatLng sydney = new LatLng(-23.537911, -46.828770);

        MarkerOptions marker = new MarkerOptions();
        marker.position(sydney);
        marker.title("LeVision");
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));
    }

    @Override
    public void onMapClick(LatLng latLng) {

        Toast.makeText(getActivity(), "Coordenadas: " + latLng.toString(), Toast.LENGTH_SHORT).show();

    }
}

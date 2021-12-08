package com.example.astroidfield;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

interface variableChangeListener{
    public void onVariableChanged(boolean var);
}

public class Leaderboards_Maps_Fragment extends Fragment {

    private boolean allreadyHavePing = false;
    private AppCompatActivity activity;
    private CallBack_Leaderboards_Scores callBackMap;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            callBackMap = new CallBack_Leaderboards_Scores() {
                @Override
                public void mapPing(double lon, double lat, String name) {

                    if(!allreadyHavePing){
                        allreadyHavePing = true;
                    }
                    else{
                        googleMap.clear();
                    }
                    LatLng latlng = new LatLng(lat, lon);
                    googleMap.addMarker(new MarkerOptions().position(latlng).title(name + " played here"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
                }
            };
        }


    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leaderboards__maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void setActivity(Leaderboards_Main activity) {
        this.activity = activity;
    }
    public void setPing(double lon, double lat, String name){
        callBackMap.mapPing(lon,lat,name);
    }

}

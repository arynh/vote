package cs125.mp7.vote;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

public class LocationMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        LatLng sydney = new LatLng(40.1164, -88.2434);

//        void startApiCall(); {
//            String url =
//                    "https://maps.googleapis.com/maps/api/geocode/json?address="
//                            + MainActivity.getParsedAddress(MainActivity.getAddress())
//                            + "&key=AIzaSyCrB8KnIKdPZ7WMLRB-IaCCmrAmQqPZXVo";
//            final android.content.Context context = getApplicationContext();
//            final Toast toast = Toast.makeText(context,
//                    "Retrieving data . . .",
//                    Toast.LENGTH_SHORT);
//            toast.show();
//            try {
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                        Request.Method.GET,
//                        url,
//                        null,
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(final JSONObject response) {
//                                Log.d("Location Error: ", response.toString());
//                                setJsonInformation(response.toString());
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(final VolleyError error) {
//                        Log.w("Location Error: ", error.toString());
//                    }
//                });
//                requestQueue.add(jsonObjectRequest);
//            } catch (Exception e) {
//                Toast toast2 = Toast.makeText(context,
//                        "Failed.",
//                        Toast.LENGTH_SHORT);
//                toast2.show();
//                e.printStackTrace();
//            }
//        }
        // TODO: get the coordinates of place here

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

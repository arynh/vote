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
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

public class LocationMap extends FragmentActivity implements OnMapReadyCallback {
    private static RequestQueue requestQueue;

    private GoogleMap mMap;
    private static String coordinates;
    private final String TAG = "LocationMapFragment";

    public static String getCoordinates() {
        return coordinates;
    }

    public static void setCoordinates(String coordinates) {
        LocationMap.coordinates = coordinates;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        requestQueue = Volley.newRequestQueue(this);
    }

    void startApiCall() {
        String url =
                "https://maps.googleapis.com/maps/api/geocode/json?address="
                        + MainActivity.getParsedAddress(MainActivity.getAddress())
                        + "&key=AIzaSyCrB8KnIKdPZ7WMLRB-IaCCmrAmQqPZXVo";
        final android.content.Context context = getApplicationContext();
        final Toast toast = Toast.makeText(context,
                "Retrieving data . . .",
                Toast.LENGTH_SHORT);
        toast.show();
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d("StartApiCall response: ", response.toString());
                            setCoordinates(response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w("Location Error: ", error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            Toast toast2 = Toast.makeText(context,
                    "Failed.",
                    Toast.LENGTH_SHORT);
            toast2.show();
            Log.e(TAG, "startApiCall: ", e);
        }
    }

    LatLng stringToLatLng(final String input) {
        LatLng coords = null;
        Log.d(TAG, "stringToLatLng: Now attempting to generate coordinates from json output");
        Log.d(TAG, "stringToLatLng: " + input);
        JsonElement jsonElement = new JsonParser().parse(input);
        try {
            JsonArray bigObj = jsonElement.getAsJsonArray();
            Double latitude = bigObj.get(0).getAsJsonObject().get("geometry").getAsJsonObject()
                    .get("location").getAsJsonObject().get("lat").getAsDouble();
            Double longitude = bigObj.get(0).getAsJsonObject().get("geometry").getAsJsonObject()
                    .get("location").getAsJsonObject().get("long").getAsDouble();
            coords = new LatLng(latitude, longitude);
        } catch (Exception e) {
            Log.e(TAG, "makeCoords: " + e.toString());
        }
        return coords;
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
        startApiCall();

        mMap = googleMap;

        float counter = 1024;
        while (getCoordinates() == null) {
            counter *= 3.14;
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = stringToLatLng(getCoordinates());

        // TODO: get the coordinates of place here

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

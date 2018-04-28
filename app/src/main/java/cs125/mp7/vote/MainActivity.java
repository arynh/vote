package cs125.mp7.vote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    private static String address = "none";

    /**
     * Google Civic Information API key
     */
    private final String apiKey = BuildConfig.API_KEY;

    public static String getAddress() {
        return address;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        final FloatingActionButton locationButton = findViewById(R.id.fab);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAlertAndRequest();
            }
        });

        Toast.makeText(MainActivity.this, "Choose address using location button -->", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate: We got dis far");
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new LocalTab(), "Local");
        adapter.addFragment(new StateTab(), "State");
        adapter.addFragment(new FederalTab(), "Federal");
        adapter.addFragment(new LocationTab(), "Polling Location");
        viewPager.setAdapter(adapter);
    }

    private void makeAlertAndRequest() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Voter Address");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                address = input.getText().toString();
                Log.d(TAG, "onClick: address set to: " + address);
                Toast.makeText(MainActivity.this, "Address Set!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Log.d(TAG, "onClick: address entry cancelled");
            }
        });

        builder.show();
    }

    public void makeInformation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Candidate Information");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                address = input.getText().toString();
                Log.d(TAG, "onClick: address set to: " + address);
                Toast.makeText(MainActivity.this, "Address Set!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }
    public void startAPICall() {
        String url = "https://www.googleapis.com/civicinfo/v2/representatives?address="
                + getAddress(address)
                + "&key=" + apiKey;
        final android.content.Context context = getApplicationContext();
        Log.d("I was called: ", url);
        try {
            JsonObjectRequest a = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d("Here is the response: ", response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w("Here is the response: ", error.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAddress(String address) {
        return address.replaceAll(" ","%20");
    }
}

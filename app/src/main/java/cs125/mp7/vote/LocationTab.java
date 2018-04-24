package cs125.mp7.vote;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 **/

public class LocationTab extends Fragment {
    private static final String TAG = "LocationTabFragment";

    // TODO: declare any buttons and whatnot up here

    TextView textView1;
    TextView textView2;
    Button button;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_tab, container, false);
        textView1 = rootView.findViewById(R.id.current_address_sign);
        textView2 = rootView.findViewById(R.id.address);
        textView1.setText(MainActivity.getAddress());
        button = rootView.findViewById(R.id.mapButton);
        button.setText("View Polling Location");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: map button clicked");
                launchMap();
            }
        });

        return rootView;
    }

    private void launchMap() {
        Intent intent = new Intent();
        try {
            intent.setClass(this.getContext(), LocationMap.class);
        } catch (NullPointerException e) {
            Log.e(TAG, "launchMap: null pointer exception");
        }
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}

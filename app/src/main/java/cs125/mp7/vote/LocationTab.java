package cs125.mp7.vote;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

/**
 * A simple {@link Fragment} subclass.
 **/

public class LocationTab extends Fragment {
    private static final String TAG = "LocationTabFragment";

    // TODO: declare any buttons and whatnot up here

    TextView textView1;
    TextView textView2;
    //ImageView imageView;
    MapView m;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_tab, container, false);
        textView1 = rootView.findViewById(R.id.current_address_sign);
        textView2 = rootView.findViewById(R.id.address);
        textView1.setText(MainActivity.getAddress());
        //imageView = rootView.findViewById(R.id.imageView);
        m = rootView.findViewById(R.id.mapView);
        m.onCreate(savedInstanceState);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        m.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        m.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        m.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        m.onLowMemory();
    }
}

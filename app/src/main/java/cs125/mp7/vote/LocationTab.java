package cs125.mp7.vote;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 **/

public class LocationTab extends Fragment {
    private static final String TAG = "LocationTabFragment";

    // TODO: declare any buttons and whatnot up here

    TextView textView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_tab, container, false);
        textView = rootView.findViewById(R.id.textView);
        return rootView;
    }
}

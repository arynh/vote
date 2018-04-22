package cs125.mp7.vote;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 **/

public class stateTab extends Fragment {
    private static final String TAG = "stateTabFragment";

    // TODO: declare any buttons and whatnot up here

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_state_tab, container, false);

        // TODO: make sure to add listeners for buttons and et cetera here

        return view;
    }
}

package cs125.mp7.vote;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * A simple {@link Fragment} subclass.
 **/

public class LocalTab extends Fragment {
    private static final String TAG = "localTabFragment";

    // TODO: declare any buttons and whatnot up here

    public void reloadTab() {
        // Reload current fragment
        Fragment frg = null;
        frg = getFragmentManager().findFragmentByTag("localTabFragment");
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }

    public CardData[] makeLocalData(final String input) {
        int numCards = 0;
        JsonArray officials;
        //try {
            JsonElement jsonElement = new JsonParser().parse(input);
            JsonObject bigObj = jsonElement.getAsJsonObject();
            officials = bigObj.get("officials").getAsJsonArray();
        //} catch (Exception e) {
        //    Log.e(TAG, "makeLocalData: " + e.toString());
        //    CardData[] data = new CardData[1];
        //    data[0] = new CardData("no", "data", "yet");
        //    return data;
        //}
        for (JsonElement official : officials) {
            numCards++;
        }
        CardData[] data = new CardData[numCards];
        String name;
        String party;
        String office;
        for (int index = 0; index < data.length; index++) {
            name = officials.get(index).getAsJsonObject().get("name").getAsString();
            party = officials.get(index).getAsJsonObject().get("party").getAsString();
            //office = officials.get(index).getAsJsonObject().get("office").getAsString();
            office = "oop";
            data[index] = new CardData(name, party, office);
        }
        return data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: creating view for local tab");
        
        View view = inflater.inflate(R.layout.fragment_local_tab, container, false);

        // TODO: make sure to add listeners for buttons and et cetera here

        Log.d(TAG, "onCreateView: Making recyclerview");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewLocal);

        Log.d(TAG, "onCreateView: making data for recycler view");

        // this is data for recycler view
        // TODO: Get data from the API; don't hardcode it lel

        Log.d(TAG, "onCreateView: setting up layout manager");

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        // 3. create an adapter
        CustomAdapter mAdapter = new CustomAdapter(makeLocalData(MainActivity.getJsonInformation()));
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Log.d(TAG, "onCreateView: now returning view");
        return view;
    }
}

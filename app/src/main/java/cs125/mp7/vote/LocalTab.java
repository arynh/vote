package cs125.mp7.vote;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
        //Log.d(TAG, "this was called");
        int numCards = 0;
        JsonArray officials;
        JsonArray offices;
        try {
            JsonElement jsonElement = new JsonParser().parse(input);
            JsonObject bigObj = jsonElement.getAsJsonObject();
            officials = bigObj.get("officials").getAsJsonArray();
            offices = bigObj.get("offices").getAsJsonArray();
        } catch (Exception e) {
            Log.e(TAG, "makeLocalData: " + e.toString());
            CardData[] data = new CardData[1];
            data[0] = new CardData("no", "data", "yet");
            return data;
        }
        for (JsonElement official : officials) {
            numCards++;
        }
        CardData[] data = new CardData[numCards];
        String name;
        String party;
        String officeData;
        JsonArray officeIndices;
        int temp = 0;
        for (JsonElement office : offices) {
              officeData = office.getAsJsonObject().get("name").getAsString();
              officeIndices = office.getAsJsonObject().get("officialIndices").getAsJsonArray();
              for (JsonElement individual : officeIndices) {
                  // Gets the official at the index.
                  try {
                      name = officials.get(individual.getAsInt()).getAsJsonObject().get("name").getAsString();
                  } catch (Exception e) {
                      name = "No Name Provided";
                  }
                  try {
                      party = officials.get(individual.getAsInt()).getAsJsonObject().get("party").getAsString();
                  } catch (Exception e) {
                      party = "No Party Provided";
                  }
                  data[temp] = new CardData(name, party, officeData);
                  temp++;
              }
        }
//        for (int index = 0; index < data.length; index++) {
//            try {
//                name = officials.get(index).getAsJsonObject().get("name").getAsString();
//            } catch (Exception e) {
//                Log.e(TAG,"Name was not avaliable");
//                name = "No Name Avaliable";
//            }
//            try {
//                party = officials.get(index).getAsJsonObject().get("party").getAsString();
//            } catch (Exception e) {
//                Log.e(TAG,"Party was not avaliable");
//                party = "No Name Avaliable";
//            }
//            //office = officials.get(index).getAsJsonObject().get("office").getAsString();
//            officeData = "President";
//            data[index] = new CardData(name, party, officeData);
//        }
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

        Log.d(TAG, "onCreateView: setting up layout manager");

        final FloatingActionButton fabReload = getView().findViewById(R.id.refreshButton);
        fabReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadTab();
            }
        });

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

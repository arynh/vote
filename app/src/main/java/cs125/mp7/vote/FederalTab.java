package cs125.mp7.vote;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 **/

public class FederalTab extends Fragment {
    private static final String TAG = "FederalTabFragment";

    // TODO: declare any buttons and whatnot up here

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: creating view for federal tab");

        View view = inflater.inflate(R.layout.fragment_local_tab, container, false);

        // TODO: make sure to add listeners for buttons and et cetera here

        Log.d(TAG, "onCreateView: Making recyclerview");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewLocal);

        Log.d(TAG, "onCreateView: making data for recycler view");

        // this is data for recycler view
        // TODO: Get data from the API; don't hardcode it lel
        CardData cardData[] = {
                new CardData("Jill Stein", "Green", "President" +
                        "\n Many informations"),
                new CardData("J.B. Pritzker", "Democrat", "Governor" +
                        "\n and many more informations"),
                new CardData("Bruce Rauner", "Republican", "Governor" +
                        "\n Hopefully we can put useful things"),
                new CardData("sth swnt", "Tea", "President" +
                        "\n inside these cards"),
                new CardData("Jill Stein", "Green", "President" +
                        "\n Many informations"),
                new CardData("J.B. Pritzker", "Democrat", "Governor" +
                        "\n and many more informations"),
                new CardData("Bruce Rauner", "Republican", "Governor" +
                        "\n Hopefully we can put useful things"),
                new CardData("sth swnt", "Tea", "President" +
                        "\n inside these cards"),
                new CardData("Jill Stein", "Green", "President" +
                        "\n Many informations"),
                new CardData("J.B. Pritzker", "Democrat", "Governor" +
                        "\n and many more informations"),
                new CardData("Bruce Rauner", "Republican", "Governor" +
                        "\n Hopefully we can put useful things"),
                new CardData("sth swnt", "Tea", "President" +
                        "\n inside these cards"),
                new CardData("Jill Stein", "Green", "President" +
                        "\n Many informations"),
                new CardData("J.B. Pritzker", "Democrat", "Governor" +
                        "\n and many more informations"),
                new CardData("Bruce Rauner", "Republican", "Governor" +
                        "\n Hopefully we can put useful things"),
                new CardData("sth swnt", "Tea", "President" +
                        "\n inside these cards")
        };

        Log.d(TAG, "onCreateView: setting up layout manager");

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        // 3. create an adapter
        CustomAdapter mAdapter = new CustomAdapter(cardData);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Log.d(TAG, "onCreateView: now returning view");
        return view;
    }
}

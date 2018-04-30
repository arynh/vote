/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cs125.mp7.vote;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private CardData[] mDataSet;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(CardData[] dataSet) {
        mDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cards_layout, viewGroup, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element

        // set data
        viewHolder.getTextView1().setText(mDataSet[position].getName());
        viewHolder.getTextView2().setText(mDataSet[position].getParty() + "\n"
                + mDataSet[position].getOffice());
        viewHolder.getTextView3().setText(mDataSet[position].getParty().substring(0, 1));
        if (mDataSet[position].getParty().equals("Democratic")) {
            viewHolder.getTextView3().setTextColor(viewHolder.getTextView3()
                    .getResources().getColor(R.color.Democrat));
        } else if (mDataSet[position].getParty().equals("Republican")) {
            viewHolder.getTextView3().setTextColor(viewHolder.getTextView3()
                    .getResources().getColor(R.color.Republican));
        } else if (mDataSet[position].getParty().equals("Green")) {
            viewHolder.getTextView3().setTextColor(viewHolder.getTextView3()
                    .getResources().getColor(R.color.Green));
        } else if (mDataSet[position].getParty().equals("Unknown")) {
            viewHolder.getTextView3().setTextColor(viewHolder.getTextView3()
                    .getResources().getColor(R.color.no_party));
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView1 = v.findViewById(R.id.label);
            textView2 = v.findViewById(R.id.size);
            textView3 = v.findViewById(R.id.textView2);
        }

        public TextView getTextView1() {
            return textView1;
        }

        public TextView getTextView2() {
            return textView2;
        }

        public TextView getTextView3() {
            return textView3;
        }
    }
}
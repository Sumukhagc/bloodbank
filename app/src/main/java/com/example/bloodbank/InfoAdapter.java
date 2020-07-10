package com.example.bloodbank;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class InfoAdapter extends ArrayAdapter<ListActivity> {
    public InfoAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public InfoAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public InfoAdapter(@NonNull Context context, int resource, @NonNull ListActivity[] objects) {
        super(context, resource, objects);
    }

    public InfoAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull ListActivity[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public InfoAdapter(@NonNull Context context, int resource, @NonNull List<ListActivity> objects) {
        super(context, resource, objects);
    }

    public InfoAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<ListActivity> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}

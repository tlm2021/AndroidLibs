package com.travismosley.uilibs.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * An ArrayAdapter with some extra convenience methods for common operations
 */
public abstract class ArrayAdapter<T> extends android.widget.ArrayAdapter<T> {

    private final static String LOG_TAG = ArrayAdapter.class.getSimpleName();

    protected abstract void populateView(View view, T item);
    protected abstract int getItemLayoutId();
    protected abstract Object createViewHolder(View vew);

    public ArrayAdapter(Context context, List<T> itemList) {
        super(context, -1, itemList);
        Log.d(LOG_TAG, "Initialize for " + itemList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflateView(convertView, parent);
        T item = getItem(position);
        populateView(view, item);

        return view;
    }

    protected View inflateView(View convertView, ViewGroup parent){
        View view;

        // Recycle the view if possible
        if (convertView != null) {
            view = convertView;
        } else {
            view = createView(parent);
        }
        return view;
    }

    protected View createView(ViewGroup parent){
        // Create a new view with a given parent. Called when there is no view to recycle

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(getItemLayoutId(), parent, false);
        view.setTag(createViewHolder(view));

        return view;
    }

    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getView(position, convertView, parent);
    }

}

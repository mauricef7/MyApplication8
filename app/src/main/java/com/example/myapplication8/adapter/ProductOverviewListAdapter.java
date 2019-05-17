package com.example.myapplication8.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication8.Database.Product;
import com.example.myapplication8.R;

import java.util.List;

public class ProductOverviewListAdapter extends ArrayAdapter<Product> {
    public ProductOverviewListAdapter(final Context context, final List<Product> objects){
        super(context,0, objects);

    }


    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent){
        Product currentProduct = getItem(position);
        View view = convertView;

        if(view ==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.product_overview_listitem, parent, false);
            }
        ((TextView) view.findViewById(R.id.category)).setText(currentProduct.getCategory());
        ((TextView) view.findViewById(R.id.name)).setText(currentProduct.getName());
        ((TextView) view.findViewById(R.id.price)).setText(Double.toString(currentProduct.getPrice()));

        return view;
    }
}

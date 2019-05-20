package com.example.myapplication8.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication8.Database.Product;
import com.example.myapplication8.Database.ShoppingCartList;
import com.example.myapplication8.MainActivity;
import com.example.myapplication8.R;
import com.example.myapplication8.ShoppingCartActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductOverviewListAdapter extends ArrayAdapter<Product> implements ListAdapter {
    private Product element;
    private Context context;
    //private List<Product> list = new ArrayList<>();

    public ProductOverviewListAdapter(final Context context, final List<Product> objects) {
        super(context, 0, objects);
        this.context = context;



    }
   /* @Override
    public int getCount(){
        return list.size();
    }*/

   /* @Override
    public Product getCurrentProduct (){
        return product;
    }*/

    @Override
    public View getView( final int position,  final View convertView,  final ViewGroup parent){
        Product currentProduct = getItem(position);

        View view = convertView;

        if(view ==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.product_overview_listitem, parent, false);
            }
        ((TextView) view.findViewById(R.id.category)).setText(currentProduct.getCategory());
        ((TextView) view.findViewById(R.id.name)).setText(currentProduct.getName());
        ((TextView) view.findViewById(R.id.price)).setText(Double.toString(currentProduct.getPrice()));




        //Add products to shopping cart
        Button addBtn = (Button)view.findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                    element = getItem(position);
                   // MainActivity.setShoppingCartList(currentProduct) ;
                    Log.i(" Edit Button Clicked", "btn clicked" + position);
                    notifyDataSetChanged();
            }
        });

        return view;
    }
 }


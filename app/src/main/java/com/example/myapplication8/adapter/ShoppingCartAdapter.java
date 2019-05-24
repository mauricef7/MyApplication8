package com.example.myapplication8.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.myapplication8.Database.Product;
import com.example.myapplication8.Database.ShoppingCartList;
import com.example.myapplication8.MainActivity;
import com.example.myapplication8.R;
import com.example.myapplication8.SetQuantityActivity;
import com.example.myapplication8.ShoppingCartActivity;


import java.util.ArrayList;

import android.support.v4.app.ActivityCompat;


public class ShoppingCartAdapter extends BaseAdapter implements ListAdapter {
    public ShoppingCartList list = new ShoppingCartList();
    private Context context;
    private Activity activity;
    private int quantity;


    public ShoppingCartAdapter(ShoppingCartList list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        if(list != null)
        return list.size();
        else {
            return 0;
        }

    }

    @Override
    public Product getItem(int pos) {
        return list.get(pos);
    }


    public long getItemId(int pos) {
        //TODO
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_shopping_cart_listitem, null);
        }

        //Handle TextView and display string from  list
        TextView listItemCategory = view.findViewById(R.id.category);
        listItemCategory.setText(getItem(position).getCategory());

        TextView listItemName = view.findViewById(R.id.name);
        listItemName.setText(getItem(position).getName());

        TextView listItemPrice = view.findViewById(R.id.price);
        listItemPrice.setText(Double.toString(getItem(position).getPrice()));

        if (getItem(position).getQuantity()!= 0){
            TextView listItemQuantity = view.findViewById(R.id.item_quantity);
            listItemPrice.setText(Double.toString(getItem(position).getQuantity()));
        }

        //Handle buttons and add onClickListeners
        ImageButton deleteBtn = view.findViewById(R.id.delete_btn);


        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });
        Button btn_quantity = view.findViewById(R.id.btn_quantity);
        btn_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SetQuantityActivity.class);


                ActivityCompat.startActivityForResult(activity , i, 1, Bundle.EMPTY);
              /*  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                    int quantity =0;
                    if (requestCode == 1) {
                        if(resultCode == Activity.RESULT_OK){
                            String result=data.getStringExtra("result");
                            quantity = Integer.parseInt(result);

                        }
                        if (resultCode == Activity.RESULT_CANCELED) {

                        }

                    }
                }*/

                getItem(position).setQuantity(quantity);
                notifyDataSetChanged();
            }
        });

        return view;
    }






}


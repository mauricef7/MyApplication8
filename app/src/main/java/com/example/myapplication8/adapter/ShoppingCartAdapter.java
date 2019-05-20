package com.example.myapplication8.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.myapplication8.Database.Product;
import com.example.myapplication8.Database.ShoppingCartList;
import com.example.myapplication8.MainActivity;
import com.example.myapplication8.R;
import com.example.myapplication8.ShoppingCartActivity;


import java.util.ArrayList;

public class ShoppingCartAdapter extends BaseAdapter implements ListAdapter {
    public ShoppingCartList list = new ShoppingCartList();
    private Context context;



    public ShoppingCartAdapter(ShoppingCartList list, Context context) {
        this.list = list;
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_shopping_cart_listitem, null);
        }

        //Handle TextView and display string from your list
        TextView listItemCategory = (TextView)view.findViewById(R.id.category);
        listItemCategory.setText(getItem(position).getCategory());

        TextView listItemName = (TextView)view.findViewById(R.id.name);
        listItemName.setText(getItem(position).getName());

        TextView listItemPrice = (TextView)view.findViewById(R.id.price);
        listItemPrice.setText(Double.toString(getItem(position).getPrice()));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);


        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });

        /*Button btn_buyAll = (Button) ShoppingCartActivity.findViewById(R.id.btn_buyAll);
        if (btn_buyAll != null){
            btn_buyAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

                    //send orders to servers to process and delete shopping cart

                  list.clearAll();
                }
            });*/


        return view;
    }
}


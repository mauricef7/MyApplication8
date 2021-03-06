package com.example.myapplication8.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.myapplication8.MainActivity;
import com.example.myapplication8.R;
import com.example.myapplication8.Database.Product;

import java.util.List;

public class NewProductOverviewListAdapter  extends BaseAdapter implements ListAdapter {
    private List<Product> list ;
    private Context context;



    public NewProductOverviewListAdapter(List<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
            final Product currentProduct = getItem(position);
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.product_overview_listitem, null);
            }

            //Handle TextView and display string from your list
            TextView listItemCategory = view.findViewById(R.id.category);
            listItemCategory.setText(getItem(position).getCategory());

            TextView listItemName = view.findViewById(R.id.name);
            listItemName.setText(getItem(position).getName());

            TextView listItemPrice = view.findViewById(R.id.price);
            listItemPrice.setText(Double.toString(getItem(position).getPrice()));

            //Handle buttons and add onClickListeners

            ImageButton addBtn = view.findViewById(R.id.add_btn);


            addBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    MainActivity.shoppingCartList.newShoppingCartItem(currentProduct);

                    Log.i(" Add Button Clicked", " btn clicked" + position);
                    notifyDataSetChanged();
                    Snackbar.make(v, R.string.Item_added_to_shopping_cart, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            });

            return view;
        }
}
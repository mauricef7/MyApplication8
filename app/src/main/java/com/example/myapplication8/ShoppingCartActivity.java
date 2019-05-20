package com.example.myapplication8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication8.Database.Product;
import com.example.myapplication8.adapter.ShoppingCartAdapter;
import com.example.myapplication8.Database.ShoppingCartList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ShoppingCartAdapter adapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Intent i = getIntent();
        if (MainActivity.shoppingCartList != null) {
            adapter = new ShoppingCartAdapter(MainActivity.shoppingCartList, this);
            ListView lView = (ListView)findViewById(R.id.shopping_cart_products_ListView);
            lView.setAdapter(adapter);
        }
        else{

            Toast.makeText(this.getBaseContext(),"No Items in shopping cart yet!",
                    Toast.LENGTH_SHORT).show();

        }

        //handle listview and assign adapter

    }








      /*  Context context = getActivity(MainActivity.class);
        SharedPreferences shoppingCart = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
     //Retrieve the value
        Gson gson = new Gson();
        String jsonText = Prefs.getString("key", null);
        String[] text = gson.fromJson(jsonText, String[].class);  //EDIT: gso to gson


//Set the values
        Gson gson = new Gson();
        List<String> textList = new ArrayList<String>();
        textList.addAll(data);
        String jsonText = gson.toJson(textList);
        prefsEditor.putString("key", jsonText);
        prefsEditor.apply();

*/


    }



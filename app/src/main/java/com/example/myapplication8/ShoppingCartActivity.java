package com.example.myapplication8;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication8.Database.Product;
import com.example.myapplication8.adapter.ShoppingCartAdapter;
import com.example.myapplication8.Database.ShoppingCartList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class ShoppingCartActivity extends AppCompatActivity {
    public  ShoppingCartList orderlist = new ShoppingCartList();
    private int quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ShoppingCartAdapter adapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Intent i = getIntent();
        if (MainActivity.shoppingCartList != null) {
            adapter = new ShoppingCartAdapter(MainActivity.shoppingCartList, this, this);
            ListView lView = findViewById(R.id.shopping_cart_products_ListView);
            lView.setAdapter(adapter);
        }
        else{

            Toast.makeText(this.getBaseContext(),getString(R.string.no_items_in_shoppingcart),
                    Toast.LENGTH_SHORT).show();

        }




//reload all products from database
        Button btn_buyAll = findViewById(R.id.btn_buyAll);
        if (btn_buyAll != null){
            btn_buyAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    orderlist = MainActivity.shoppingCartList;
                    if(orderlist != null) {


                        Snackbar.make(view, R.string.bought_all, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        //send orders to servers to process and delete shopping cart

                        // TODO clear listView: ShoppingCartAdapter.list.clearAll();
                        MainActivity.shoppingCartList.clearAll();
                        orderNotify();
                    }
                    else {
                        Snackbar.make(view, R.string.failure_shopping_cart_empty, Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                }
        });
    }

    }
    private void orderNotify() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), pendingIntent);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int quantity =0;
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                quantity = Integer.parseInt(result);

                //Log.i("result of OnActivityResult: " + result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }

        }
    }


}












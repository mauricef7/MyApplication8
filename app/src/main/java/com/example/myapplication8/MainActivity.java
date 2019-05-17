package com.example.myapplication8;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication8.Database.Product;
import com.example.myapplication8.adapter.ProductOverviewListAdapter;
import com.example.myapplication8.Database.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView                   listView;
    private List<Product>              dataSource;
    private ProductOverviewListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TODO", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//For Nav Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //For product data:
        this.listView = (ListView) findViewById(R.id.products);
        this.dataSource = ProductDatabase.getInstance(this).readAllProducts();
        this.adapter = new ProductOverviewListAdapter(this, dataSource);
        this.listView.setAdapter(adapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                Object element = adapterView.getAdapter().getItem(i);

      // For shopping cart :
                //if (element instanceof Product) {
                //  Product todo = (Product) element;

                //     Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                //    intent.putExtra(ShoppingCartActivity.Product, product.getId());

                //    startActivity(intent);
                //}
                Log.e("ClickOnList", element.toString());
            }
            });
    }
    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    private void refreshListView() {
        dataSource.clear();
        dataSource.addAll(ProductDatabase.getInstance(this).readAllProducts());
        adapter.notifyDataSetChanged();
    }
    public void clearAll() {
        ProductDatabase database = ProductDatabase.getInstance(MainActivity.this);
        database.deleteAllProducts();
        refreshListView();
    }

    //public void newProduct(){
    //    Intent i = new Intent(MainActivity.this, AddNewProducts.class);
    //    startActivity(i);}


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_shopping_cart) {

        } else if (id == R.id.nav_all_products) {

        } else if (id == R.id.nav_hats) {

        } else if (id == R.id.nav_shoes) {

        } else if (id == R.id.act_logout) {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_account) {
            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_orders) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

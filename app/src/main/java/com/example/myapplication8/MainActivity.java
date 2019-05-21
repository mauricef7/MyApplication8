package com.example.myapplication8;

import android.Manifest;

import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication8.Database.Product;
import com.example.myapplication8.Database.ShoppingCartList;
import com.example.myapplication8.adapter.NewProductOverviewListAdapter;
import com.example.myapplication8.Database.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView                   listView;
    private List<Product>              dataSource;
    private NewProductOverviewListAdapter adapter;
    public static ShoppingCartList shoppingCartList = new ShoppingCartList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE},1);

        setContentView(R.layout.activity_main);


        this.listView = (ListView) findViewById(R.id.products);

        Button add_btn = (Button) findViewById(R.id.add_btn);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //shoppingCartList = new ShoppingCartList();

        this.dataSource = ProductDatabase.getInstance(this).readAllProducts();

        this.adapter = new NewProductOverviewListAdapter(dataSource, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.products);
        lView.setAdapter(adapter);

        //reload all products from database
        if (fab != null){
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    Snackbar.make(view, R.string.refresh_products, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    ProductDatabase database = ProductDatabase.getInstance(MainActivity.this);

                    clearAll();
//Locally stored on device, To do: Replace with Webview
                    database.createProduct(new Product("shoe, " , " white shoe EUR " , 200.00));
                    database.createProduct(new Product("shoe, " , " brown Pantoffeln EUR " , 100.00));
                    database.createProduct(new Product("shoe, " , " white shoe EUR " , 200.00));
                    database.createProduct(new Product("shoe, " , " brown Pantoffeln EUR " , 100.00));
                    database.createProduct(new Product("shoe, " , " black Schuh EUR " , 250.00));
                    database.createProduct(new Product("shoe, " , " Oxford shoe EUR " , 240.00));
                    database.createProduct(new Product("shoe, " , " Monk Business EUR " , 250.00));
                    database.createProduct(new Product("shoe, " , " Policeman shoe EUR " , 300.00));
                    database.createProduct(new Product("shoe, " , " Clown shoe EUR " , 900.00));
                    database.createProduct(new Product("shoe, " , " Big AF shoe EUR " , 1100.00));
                    database.createProduct(new Product("shoe, " , " Dwarf Pantoffeln EUR " , 900.00));
                    database.createProduct(new Product("shoe, " , " Sandale EUR " , 20.00));
                    database.createProduct(new Product("hat, " , " Baseballcap  EUR " , 290.00));
                    database.createProduct(new Product("hat, " , " Bischofshut EUR " , 200.00));
                    database.createProduct(new Product("hat, " , " Pastor's hat Pantoffeln EUR " , 100.00));
                    database.createProduct(new Product("hat, " , " black hat EUR " , 25.00));
                    database.createProduct(new Product("hat, " , " Oxford hat EUR " , 240.00));
                    database.createProduct(new Product("hat, " , " Monk hat EUR " , 25.00));
                    database.createProduct(new Product("hat, " , " Policeman cap EUR " , 30.00));
                    database.createProduct(new Product("hat, " , " Clown cap EUR " , 90.00));
                    database.createProduct(new Product("hat, " , " Big AF witchhat EUR " , 1100.00));
                    database.createProduct(new Product("hat, " , " Dwarf hat EUR " , 90.00));
                    database.createProduct(new Product("hat, " , " Mütze EUR " , 20.00));
                    database.createProduct(new Product("hat, " , " Spitzhut  EUR " , 290.00));

                    refreshListView();

                }
            });
        }


//For Nav Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //protected void notify(Context context );



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_shopping_cart) {



            Intent i = new Intent(this, ShoppingCartActivity.class);



            startActivity(i);


        } else if (id == R.id.nav_all_products) {



        } else if (id == R.id.act_logout) {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_account) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.example.myapplication8.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ProductDatabase extends SQLiteOpenHelper {
    public static ProductDatabase INSTANCE = null;
    private static final String DB_NAME = "ProductsDB";
    private static final int VERSION = 1;
    public static final String TABLE_NAME = "products";

    private static final String ID_COLUMN = "ID";
    private static final String CATEGORY_COLUMN = "category";
    private static final String NAME_COLUMN = "name";
    private static final String PRICE_COLUMN = "price";


    private ProductDatabase(final Context context) {
        super(context, DB_NAME, null, VERSION);
    }
    public static ProductDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ProductDatabase(context);
        }

        return INSTANCE;
    }
    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
            String createQuery = "CREATE TABLE " + TABLE_NAME + " (" + ID_COLUMN + "INTEGER PRIMARY KEY, " + CATEGORY_COLUMN + " TEXT NOT NULL, "
                    + NAME_COLUMN + " TEXT NOT NULL, " + PRICE_COLUMN +  " DOUBLE NOT NULL)";

            sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int i, final int i1) {
        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(dropTable);

        onCreate(sqLiteDatabase);

    }
    public Product createProduct (final Product product){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CATEGORY_COLUMN, product.getCategory());
        values.put(NAME_COLUMN, product.getName());
        values.put(PRICE_COLUMN, product.getPrice());

        long newID = database.insert(TABLE_NAME, null, values);

        database.close();

        return readProduct(newID);
    }
    public Product readProduct (final long id){
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor = database.query(TABLE_NAME, new String[]{ID_COLUMN, CATEGORY_COLUMN, NAME_COLUMN, PRICE_COLUMN}, ID_COLUMN + " = ?"
                    , new String[] {String.valueOf(id)}, null, null, null);

            Product product = null;

            if (cursor != null && cursor.getCount() > 0 ){
                cursor.moveToFirst();
                product = new Product(cursor.getString(cursor.getColumnIndex(CATEGORY_COLUMN)));
                product.setId(cursor.getLong(cursor.getColumnIndex(ID_COLUMN)));
                product.setName(cursor.getString(cursor.getColumnIndex(NAME_COLUMN)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndex(PRICE_COLUMN)));

                }
            database.close();
            return product;
    }

    public List<Product> readAllProducts(){
        List<Product> products = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = readProduct(cursor.getLong(cursor.getColumnIndex(ID_COLUMN)));
                if (product != null) {
                    products.add(product);
                }
            } while (cursor.moveToNext());
        }

        database.close();

        return products;

    }
    public Product updateProduct (final Product product){
        SQLiteDatabase database = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(CATEGORY_COLUMN, product.getCategory());
        values.put(NAME_COLUMN, product.getName());
        values.put(PRICE_COLUMN, product.getPrice());

        database.update(TABLE_NAME, values, ID_COLUMN + " = ?", new String[]{String.valueOf(product.getId())});

        database.close();

        return this.readProduct(product.getId());

    }
    public void deleteProduct (final Product product){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, ID_COLUMN + " = ?", new String[]{String.valueOf(product.getId())});
        database.close();

    }
    public void deleteAllProducts () {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME);
        database.close();

    }
    public Cursor getAllProductsAsCursor() {
        return this.getReadableDatabase().rawQuery("SELECT " + ID_COLUMN + " as _id, " + CATEGORY_COLUMN + ","
                + NAME_COLUMN + ","+ PRICE_COLUMN +" FROM " + TABLE_NAME, null);
    }

    public Product getFirstTodo() {
        List<Product> products = this.readAllProducts();

        if (products.size() > 0) {
            return products.get(0);
        }

        return null;
    }
}

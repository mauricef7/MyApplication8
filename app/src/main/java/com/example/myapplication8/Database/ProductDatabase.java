package com.example.myapplication8.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Constructor;

public class ProductDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "manufakturLadenDB";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "products"

    private static final String ID_COLUMN = "ID";
    private static final String CATEGORY_COLUMN = "category";
    private static final  String NAME_COLUMN = "name";
    private static final String PRICE_COLUMN = "price";


    public ProductDatabase(final Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
            String createQuery = "CREATE TABLE " + TABLE_NAME + " (" + ID_COLUMN + "INTEGER PRIMARY KEY, " + CATEGORY_COLUMN + " TEXT NOT NULL, "
                    + NAME_COLUMN + " TEXT NOT NULL, " + PRICE_COLUMN +  " INTEGER NOT NULL, "

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

        return readProduct((newID);
    }
    public Product readProduct (final long id){
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor = database.query(TABLE_NAME, new String[]{ID_COLUMN, CATEGORY_COLUMN, NAME_COLUMN, PRICE_COLUMN}, ID_COLUMN + " = ?"
                    , new String[] {String.valueOf(id)}, null, null, null);

            Product product = null;

            if (cursor != null && cursor.getCount() > 0 ){
                cursor.moveToFirst();
                Product product = new Product(cursor.getString(cursor.getColumnIndex(CATEGORY_COLUMN)));
                product.setId(cursor.getLong(cursor.getColumnIndex(ID_COLUMN)));

                Price price = null;

                if(!cursor.isNull(cursor.getColumnIndex(PRICE_COLUMN))){
                    price = Price.getInstance();
                    price.setPrice
                }

                product.setPrice(price);
            }
            return product;
    }
    public List<Product> readAllProducts(){

    }
    public Product updateProduct (final Product product){

    }
    public void deleteProduct (final Product product){

    }
    public void deleteAllProducts () {

    }
}

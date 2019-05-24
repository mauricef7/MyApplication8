package com.example.myapplication8;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetQuantityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_quantity);
        Button btn_setQuantity = findViewById(R.id.btn_setQuantity);

        if (btn_setQuantity != null) {
            btn_setQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    EditText quantity = (EditText) findViewById(R.id.quantity);

                    if (quantity != null) {
                        String result = quantity.getText().toString();

                        Intent returnIntent = new Intent();

                        returnIntent.putExtra("result", result);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    } else {

                        Snackbar.make(view, R.string.failure_quantity_empty, Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                }
            });
        }
    }
}

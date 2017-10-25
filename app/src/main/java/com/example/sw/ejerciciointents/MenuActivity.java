package com.example.sw.ejerciciointents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PURCHASE_REQUEST_CODE = 1;
    private static final int MAX_QUANTITY_VALUE = 1000;

    private Spinner spQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        spQuantity = (Spinner) findViewById(R.id.menu_spinner_quantity);
        populateSpinner();

        Button buy = (Button) findViewById(R.id.menu_buy);
        buy.setOnClickListener(this);
    }

    private void populateSpinner() {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item);
        for(int i = 0; i < MAX_QUANTITY_VALUE; i++){
            adapter.add(i);
        }
        spQuantity.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.menu_buy){
            int quantity = (Integer) spQuantity.getSelectedItem();
            Log.d("debug", "onClick: " + quantity);
            if(quantity <= 0){
                return;
            }
            Intent intent = new Intent(MenuActivity.this, PurchaseActivity.class);

            intent.putExtra("quantity", quantity);

            startActivityForResult(intent, PURCHASE_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PURCHASE_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                int quantity = data.getIntExtra("quantity", 1);
                String message = getResources().getString(R.string.purchase_purchased, quantity);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Purchase cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }
}

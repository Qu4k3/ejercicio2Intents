package com.example.sw.ejerciciointents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PURCHASE_REQUEST_CODE = 1;
    private static final int MAX_QUANTITY_VALUE = 20;
    private static final int MINIMUM_PURCHASE = 1;

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
        // To populate a Spinner we have to use an Adapter. The most basic adapter is the
        // ArrayAdapter which is populated just like a list. The two things we have to specify
        // to an adapter are the Context, which just like we usually do in this case it's the Application Context
        // and a layout, in this case we use a default android layout. This layout can be one
        // of the many android layouts or a custom layout. Adapters are an important part of android
        // development, they are used to bridge between views and data, we will be seeing them a lot
        // throughout the year.
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
            if(quantity < MINIMUM_PURCHASE){
                // We should display an error message in the form of a Toast or AlertDialog
                // This is left for future iterations
                return;
            }
            Intent intent = new Intent(MenuActivity.this, PurchaseActivity.class);
            intent.putExtra(getResources().getString(R.string.menu_purchase_quantity), quantity);
            startActivityForResult(intent, PURCHASE_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // We use the requestCode to determine which activity we have returned from. The resultCode
        // is used to determine if the action was successful or not. And the Intent is used to get
        // information back from the called Activity.
        if(requestCode == PURCHASE_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                int quantity = data.getIntExtra(getResources().getString(R.string.menu_purchase_quantity), 1);
                String message = getResources().getString(R.string.purchase_purchased, quantity);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                spQuantity.setSelection(0);
            }else{
                Toast.makeText(getApplicationContext(), R.string.purchase_cancelled, Toast.LENGTH_LONG).show();
            }
        }
    }
}

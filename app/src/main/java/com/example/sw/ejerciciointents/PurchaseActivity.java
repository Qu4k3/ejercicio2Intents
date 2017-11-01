package com.example.sw.ejerciciointents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PurchaseActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int MAX_QUANTITY_VALUE = 20;
    private static final int MINIMUM_PURCHASE = 1;

    private Spinner spQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        Intent intent = getIntent();

        int quantity = intent.getIntExtra(getResources().getString(R.string.menu_purchase_quantity), 1);

        spQuantity = (Spinner) findViewById(R.id.purchase_spinner_quantity);
        populateSpinner(quantity);

        Button cancel = (Button) findViewById(R.id.purchase_cancel);
        cancel.setOnClickListener(this);

        Button purchase = (Button) findViewById(R.id.purchase_buy);
        purchase.setOnClickListener(this);
    }

    private void populateSpinner(int quantity) {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item);
        for(int i = 0; i < MAX_QUANTITY_VALUE; i++){
            adapter.add(i);
        }
        spQuantity.setAdapter(adapter);
        // After populating the Spinner we now have to select the item that was selected in the
        // previous activity. To do so we must use the adapter, this is the object that knows where
        // a specific item is located.
        spQuantity.setSelection(adapter.getPosition(quantity));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.purchase_cancel:
                this.setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.purchase_buy:
                int quantity = (Integer) spQuantity.getSelectedItem();
                if(quantity < MINIMUM_PURCHASE){
                    return;
                }
                // In this case the user has purchased X items and we have to return the amount purchased
                // to the previous activity. To do so it is not enough to just set a result we must also
                // return an intent with the data we are passing. To do this we create a new Intent()
                // populate it and when we set the result we also set an Intent to be returned.
                Intent intent = new Intent();
                intent.putExtra(getResources().getString(R.string.menu_purchase_quantity), quantity);
                this.setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}

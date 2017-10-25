package com.example.sw.ejerciciointents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PurchaseActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int MAX_QUANTITY_VALUE = 1000;

    private Spinner spQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        Intent intent = getIntent();

        int quantity = intent.getIntExtra("quantity", 1);

        spQuantity = (Spinner) findViewById(R.id.purchase_spinner_quantity);
        populateSpinner();
        spQuantity.setSelection(quantity);

        Button cancel = (Button) findViewById(R.id.purchase_cancel);
        cancel.setOnClickListener(this);

        Button purchase = (Button) findViewById(R.id.purchase_buy);
        purchase.setOnClickListener(this);
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
        switch (view.getId()){
            case R.id.purchase_cancel:
                this.setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.purchase_buy:
                int quantity = (Integer) spQuantity.getSelectedItem();
                if(quantity <= 0){
                    return;
                }
                Intent intent = getIntent();
                intent.putExtra("quantity", quantity);
                this.setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}

package com.example.sw.ejerciciointents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PurchaseActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        Intent intent = getIntent();

        int quantity = intent.getIntExtra("quantity", 1);

        etQuantity = (EditText) findViewById(R.id.purchase_choose_quantity);
        etQuantity.setText(String.valueOf(quantity));

        Button cancel = (Button) findViewById(R.id.purchase_cancel);
        cancel.setOnClickListener(this);

        Button purchase = (Button) findViewById(R.id.purchase_buy);
        purchase.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.purchase_cancel:
                this.setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.purchase_buy:
                if(etQuantity.getText().toString().isEmpty()){
                    return;
                }
                this.setResult(RESULT_OK);
                Intent intent = getIntent();
                intent.putExtra("quantity", Integer.valueOf(etQuantity.getText().toString()));
                finish();
                break;
        }
    }
}

package com.example.sw.ejerciciointents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PURCHASE_REQUEST_CODE = 1;

    private EditText etQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        etQuantity = (EditText) findViewById(R.id.menu_choose_quantity);

        Button buy = (Button) findViewById(R.id.menu_buy);
        buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.menu_buy){
            if(etQuantity.getText().toString().isEmpty()){
                //empty -> return
                return;
            }
            int quantity = Integer.valueOf(etQuantity.getText().toString());
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
                String message = "Purchased " + quantity + " objects";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Purchase cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }
}

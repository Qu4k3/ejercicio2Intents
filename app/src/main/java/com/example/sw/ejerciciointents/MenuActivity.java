package com.example.sw.ejerciciointents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etQuantity;
    //private TextView tvMaxQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        etQuantity = (EditText) findViewById(R.id.menu_choose_quantity);
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

        }
    }
}

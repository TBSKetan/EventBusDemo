package com.example.eventbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<CartEvent> cartEventList  = new ArrayList<>();
    TextView cartTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cartTextView = findViewById(R.id.txtCart);
    }
    @Subscribe
    public void onCartItemAdd(CartEvent cartEvent){
        cartEventList.add(cartEvent);
        String cartTotalItems = "Cart Items: "+cartEventList.size();
        cartTextView.setText(cartTotalItems);
        Toast.makeText(this, "Item added to cart.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void open(View view) {
        Intent secondActivity = new Intent(this, SecondActivity.class);
        startActivity(secondActivity);
    }
}
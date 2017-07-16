package com.batchmates.android.darkwaters.view.thirdactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.batchmates.android.darkwaters.R;
import com.batchmates.android.darkwaters.injection.thirdactivity.DaggerThirdActivityComponent;
import com.batchmates.android.darkwaters.model.ClosePlaces;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DisplayActivity extends AppCompatActivity implements ThirdActivityContract.View{

    private static final String TAG = "wooi";
    @Inject ThirdActivityPresenter presenter;

    private ImageView imageView, placeStars;
    private TextView name,adress,phone, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        imageView=(ImageView)findViewById(R.id.imageBackground);
        placeStars=(ImageView)findViewById(R.id.placeStars);
        name=(TextView)findViewById(R.id.placeName);
        adress=(TextView)findViewById(R.id.placeAdress);
        phone=(TextView)findViewById(R.id.placePhone);
        price=(TextView)findViewById(R.id.placePrice);

        setUpDagger();

        name.setText(getIntent().getStringExtra("name"));
        adress.setText(getIntent().getStringExtra("address"));
        phone.setText(getIntent().getStringExtra("phone"));
        int price3=getIntent().getIntExtra("price",0);
        price.setText(String.valueOf(price3));
        float stars=getIntent().getFloatExtra("stars",0);
        if(stars<1)
        {
            Glide.with(this).load("https://www.7boats.com/academy/wp-content/uploads/2017/02/Review-Stars.png").into(placeStars);

        }
        else
        {

        }
        Log.d(TAG, "onCreate: "+price3+" "+stars);


        presenter.addview(this);

        presenter.setUpDisplay(imageView,name,adress,phone,price,placeStars);
    }

    private void setUpDagger() {
        DaggerThirdActivityComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }
}

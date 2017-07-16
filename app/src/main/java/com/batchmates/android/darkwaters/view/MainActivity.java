package com.batchmates.android.darkwaters.view;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.batchmates.android.darkwaters.R;
import com.batchmates.android.darkwaters.injection.mainactivity.DaggerMainActivityComponent;
import com.batchmates.android.darkwaters.model.YourShip;
import com.batchmates.android.darkwaters.view.secondactivity.MapsActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{

    private static final String TAG = "Main Activity";
    private static final int MY_PERMISSIONS_REQUEST_REQUEST_LOCATION = 0;
//    private Dialog dialog;
    private AlertDialog.Builder dialog;
//    @Inject MainActivityPresenter presenter;
    @Inject MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupDagger();
        presenter.addview(this);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: Permission not granted");

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.d(TAG, "onCreate: We need this");

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_REQUEST_LOCATION);
                Log.d(TAG, "onCreate: Requesting permission");

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
                return;
            }
        }

    }

    private void setupDagger() {
        DaggerMainActivityComponent.create().inject(this);
    }

    public void startYourAdventure(View view) {
        Log.d(TAG, "startYourAdventure: Starting your Adventure");
        final AlertDialog.Builder diaAlert=new AlertDialog.Builder(this);
        diaAlert.setTitle("Create A Captain");
        LayoutInflater inflater = getLayoutInflater();
        final View captainView = inflater.inflate(R.layout.create_a_captain, null);
        diaAlert.setView(captainView);
        diaAlert.setPositiveButton("Create Captain", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                TextInputEditText shipName=captainView.findViewById(R.id.shipName);
                TextInputEditText captainName=captainView.findViewById(R.id.captainName);

                presenter.setUpCaptain(shipName.getText().toString(),captainName.getText().toString());
                Log.d("Default", "onClick: Yep ");
            }
        });
        diaAlert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Default", "onClick: Nope");
            }
        });
        AlertDialog show=diaAlert.create();
        show.show();

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void setFullName(String fullName) {

    }

    @Override
    public void readyToSetSail(YourShip yourShip) {
        Toast.makeText(this, "Welcome Captain "+yourShip.getCaptainName(), Toast.LENGTH_LONG).show();
        Log.d(TAG, "readyToSetSail: "+yourShip.getShipName()+ " "+ yourShip.getCaptainName());
        Intent intent=new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


}

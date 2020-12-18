package com.infostackresearch.homefit.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.adapters.DeliveryLocationAdapter;
import com.infostackresearch.homefit.http.APIService;
import com.infostackresearch.homefit.http.ClientInstance;
import com.infostackresearch.homefit.models.AddressData;
import com.infostackresearch.homefit.models.DeliveryLocation;
import com.infostackresearch.homefit.sessions.UserSessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryAddressActivity extends AppCompatActivity {
    RecyclerView rv_deliverylocation;
    List<DeliveryLocation> deliveryLocationList = new ArrayList<>();
    FloatingActionButton fab_add;
    private ProgressDialog progressDialog;

    UserSessionManager session;
    private String auth_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryaddress);
        session = new UserSessionManager(DeliveryAddressActivity.this);
        HashMap<String, String> userdata = session.getUserDetails();
        auth_token = userdata.get(UserSessionManager.KEY_AuthToken);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Choose Address");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rv_deliverylocation = findViewById(R.id.rv_deliverylocation);
        fab_add = findViewById(R.id.fab_add);

        progressDialog = new ProgressDialog(DeliveryAddressActivity.this);
        progressDialog.setMessage("Fetching your saved addresses....");
        progressDialog.show();
        getDeliveryLocationList();

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeliveryAddressActivity.this, NewAddressActivity.class);
                startActivity(intent);
            }
        });

//        deliveryLocationList.add(new DeliveryLocation("1", "331, Lokmanya Nagar, Solapur", "Alexandar Pierce"));
//        deliveryLocationList.add(new DeliveryLocation("2", "16, Hotgi Road, Solapur", "Waseem"));
//        deliveryLocationList.add(new DeliveryLocation("3", "3B, Downhill, New York", "Vishal"));
//        deliveryLocationList.add(new DeliveryLocation("4", "5A, Malibu Club, California", "John Doe"));


    }

    private void getDeliveryLocationList() {
        APIService service = ClientInstance.getRetrofitInstance().create(APIService.class);
        Call<AddressData> call = service.getAddresses("Bearer " + auth_token);
        call.enqueue(new Callback<AddressData>() {
            @Override
            public void onResponse(Call<AddressData> call, Response<AddressData> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        deliveryLocationList = response.body().getDeliveryLocationList();
                        rv_deliverylocation.setAdapter(new DeliveryLocationAdapter(DeliveryAddressActivity.this, deliveryLocationList));
                        rv_deliverylocation.setLayoutManager(new GridLayoutManager(DeliveryAddressActivity.this, 2));
                    } else {
                        SweetAlertDialog pDialog = new SweetAlertDialog(DeliveryAddressActivity.this, SweetAlertDialog.ERROR_TYPE);
                        pDialog.setTitleText("Error");
                        pDialog.setContentText("Error in fetching addresses");
                        pDialog.show();
                    }
                } else {
                    SweetAlertDialog pDialog = new SweetAlertDialog(DeliveryAddressActivity.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.setTitleText("Server Error");
                    pDialog.setContentText("Error in fetching addresses");
                    pDialog.show();
                }
            }

            @Override
            public void onFailure(Call<AddressData> call, Throwable t) {
                progressDialog.dismiss();
                SweetAlertDialog pDialog = new SweetAlertDialog(DeliveryAddressActivity.this, SweetAlertDialog.ERROR_TYPE);
                pDialog.setTitleText("Server error");
                pDialog.setContentText("Internal server error " + t.getMessage());
                pDialog.show();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
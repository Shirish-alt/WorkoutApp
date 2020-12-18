package com.infostackresearch.homefit.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.http.APIService;
import com.infostackresearch.homefit.http.ClientInstance;
import com.infostackresearch.homefit.models.CityData;
import com.infostackresearch.homefit.models.DeliveryLocation;
import com.infostackresearch.homefit.models.StateData;
import com.infostackresearch.homefit.models.StatesAndCities;
import com.infostackresearch.homefit.models.UserAddress;
import com.infostackresearch.homefit.sessions.UserSessionManager;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewAddressActivity extends AppCompatActivity {
    TextInputEditText tie_deliverto, tie_mobilenumber, tie_pincode, tie_address;
    MaterialAutoCompleteTextView act_state, act_city;
    SearchableSpinner sp_state, sp_city;
    Button btn_addaddress;
    private List<StatesAndCities> states = new ArrayList<>();
    private String state_id;
    private String city_id;
    private String auth_token;
    private UserSessionManager sessionManager;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaddress);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add new address");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new UserSessionManager(NewAddressActivity.this);
        if (sessionManager.checkLogin())
            finish();
        HashMap<String, String> user = sessionManager.getUserDetails();
        auth_token = user.get(UserSessionManager.KEY_AuthToken);

        tie_deliverto = findViewById(R.id.tie_deliverto);
        tie_mobilenumber = findViewById(R.id.tie_mobilenumber);
        tie_pincode = findViewById(R.id.tie_pincode);
        tie_address = findViewById(R.id.tie_address);
        sp_state = findViewById(R.id.sp_state);
//        act_state = findViewById(R.id.act_state);
        sp_city = findViewById(R.id.sp_city);
        btn_addaddress = findViewById(R.id.btn_addaddress);

        sp_state.setTitle("Select State");
        sp_city.setTitle("Select City");
//        sp_state.setPositiveButton("OK");

//        String[] state = {"Maharashtra", "Karnataka", "Goa", "Delhi",
//                "Punjab", "Hariyana", "Uttar Pradesh", "Andhra Pradesh", "Tamilnadu"};


        getAllStates();

        btn_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    progressDialog = new ProgressDialog(NewAddressActivity.this);
                    progressDialog.setMessage("Saving your address....");
                    progressDialog.show();
                    addAddress();
                    finish();
                }
            }
        });

    }

    private void getAllStates() {
        APIService service = ClientInstance.getRetrofitInstance().create(APIService.class);
        Call<StateData> call = service.getStates();
        call.enqueue(new Callback<StateData>() {
            @Override
            public void onResponse(Call<StateData> call, Response<StateData> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        states = response.body().getStates_and_cities();
                        ArrayAdapter<StatesAndCities> adapter = new ArrayAdapter<StatesAndCities>(NewAddressActivity.this, android.R.layout.simple_spinner_dropdown_item, response.body().getStates_and_cities());
                        sp_state.setAdapter(adapter);

                        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                StatesAndCities states = (StatesAndCities) adapterView.getSelectedItem();
                                state_id = states.getId();
                                getAllCities();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<StateData> call, Throwable t) {

            }
        });
    }

    private void getAllCities() {
        APIService service = ClientInstance.getRetrofitInstance().create(APIService.class);
        Call<CityData> call = service.getCities();
        call.enqueue(new Callback<CityData>() {
            @Override
            public void onResponse(Call<CityData> call, Response<CityData> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        ArrayAdapter<StatesAndCities> cityadapter = new ArrayAdapter<StatesAndCities>(NewAddressActivity.this, android.R.layout.simple_spinner_dropdown_item, response.body().getCityDetails().getCitiesList());
                        sp_city.setAdapter(cityadapter);
                        Log.d("Adapter", "Adapter set");

                        sp_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                StatesAndCities cities = (StatesAndCities) adapterView.getSelectedItem();
                                city_id = cities.getId();
//                                Toast.makeText(NewAddressActivity.this, city_id + "", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<CityData> call, Throwable t) {

            }
        });
    }

    private void addAddress() {
        APIService service = ClientInstance.getRetrofitInstance().create(APIService.class);
        DeliveryLocation deliveryLocation = new DeliveryLocation(tie_address.getText().toString(), tie_deliverto.getText().toString(), state_id, city_id, tie_pincode.getText().toString(), tie_mobilenumber.getText().toString());
        Call<UserAddress> call = service.saveAddress(deliveryLocation, "Bearer " + auth_token);
        call.enqueue(new Callback<UserAddress>() {
            @Override
            public void onResponse(Call<UserAddress> call, Response<UserAddress> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        Toast.makeText(NewAddressActivity.this, "Address added successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NewAddressActivity.this, "Error in address storing!", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<UserAddress> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NewAddressActivity.this, "Internal server error!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private boolean isValid() {
        boolean validationFlag;

        if (!tie_deliverto.getText().toString().isEmpty() && tie_deliverto.getText().toString() != "") {
            validationFlag = true;
        } else {
            validationFlag = false;
            tie_deliverto.setError("Please enter full name of recepient.");
            return validationFlag;
        }

        if (!tie_mobilenumber.getText().toString().isEmpty() && tie_mobilenumber.getText().toString() != "") {
            validationFlag = true;
        } else {
            validationFlag = false;
            tie_mobilenumber.setError("Please enter 10-digit mobile number so we can call for resolving delivery issues.");
            return validationFlag;
        }

        if (!tie_pincode.getText().toString().isEmpty() && tie_pincode.getText().toString() != "") {
            validationFlag = true;
        } else {
            validationFlag = false;
            tie_pincode.setError("Please enter 6-digit PINcode.");
            return validationFlag;
        }

        if (!state_id.isEmpty() && state_id != "") {
            validationFlag = true;
        } else {
            validationFlag = false;
            Toast.makeText(NewAddressActivity.this, "Please select state.", Toast.LENGTH_SHORT).show();
            return validationFlag;
        }

        if (!city_id.isEmpty() && city_id != "") {
            validationFlag = true;
        } else {
            validationFlag = false;
            Toast.makeText(NewAddressActivity.this, "Please select city.", Toast.LENGTH_SHORT).show();
            return validationFlag;
        }

        return validationFlag;
    }

}
package com.infostackresearch.homefit.http;

import com.infostackresearch.homefit.models.AddressData;
import com.infostackresearch.homefit.models.CityData;
import com.infostackresearch.homefit.models.DeliveryLocation;
import com.infostackresearch.homefit.models.LoginData;
import com.infostackresearch.homefit.models.LoginModel;
import com.infostackresearch.homefit.models.PayData;
import com.infostackresearch.homefit.models.PaymentHash;
import com.infostackresearch.homefit.models.PlanResponse;
import com.infostackresearch.homefit.models.ProfileSubscription;
import com.infostackresearch.homefit.models.SignUp;
import com.infostackresearch.homefit.models.StateData;
import com.infostackresearch.homefit.models.SubscribeUser;
import com.infostackresearch.homefit.models.SubscriptionData;
import com.infostackresearch.homefit.models.UserAddress;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    //    @FormData
    @Headers("Content-Type: application/json")
    @POST("admin-login")
    Call<LoginModel> doLogin(@Body LoginData loginData);

    //    @FormUrlEncoded
    @GET("customer/active-sp")
    Call<PlanResponse> getAllPlans();

    @POST("customer-register")
    Call<SignUp> doSignUp(@Body LoginData loginData);

    @Headers("Content-Type: application/json")
    @GET("customer/sp/user-sp")
    Call<ProfileSubscription> getProfile(@Header("Authorization") String authToken);

    @Headers("Content-Type: application/json")
    @POST("get-hash")
    Call<PaymentHash> getHash(@Body PayData payData);

    @Headers("Content-Type: application/json")
    @POST("customer/user-subscribe")
    Call<SubscribeUser> doSubscription(@Body SubscriptionData subscriptionData, @Header("Authorization") String auth_token);

    @GET("states")
    Call<StateData> getStates();

    @GET("cities")
    Call<CityData> getCities();

    @Headers("Content-Type: application/json")
    @POST("address")
    Call<UserAddress> saveAddress(@Body DeliveryLocation deliveryLocation, @Header("Authorization") String auth_token);

    @GET("customer-addresses")
    Call<AddressData> getAddresses(@Header("Authorization") String auth_token);
}

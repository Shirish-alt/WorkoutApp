package com.infostackresearch.homefit.http;

import com.infostackresearch.homefit.models.LoginData;
import com.infostackresearch.homefit.models.LoginModel;
import com.infostackresearch.homefit.models.PayData;
import com.infostackresearch.homefit.models.PaymentHash;
import com.infostackresearch.homefit.models.PlanResponse;
import com.infostackresearch.homefit.models.ProfileSubscription;
import com.infostackresearch.homefit.models.SignUp;

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

}

package com.wakarkhan.deliverydropnavigation;

import retrofit2.Call;
import com.wakarkhan.deliverydropnavigation.ServerRequest;
import com.wakarkhan.deliverydropnavigation.Models.ServerResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by HP on 27-10-2016.
 */
public interface RequestInterface {

    @POST("deliverydrop/maindirectory")
    Call<ServerResponse> operation(@Body ServerRequest request);

}

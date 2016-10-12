package com.wakarkhan.deliverydropbeta;

import retrofit2.http.POST;
import com.wakarkhan.deliverydropbeta.models.ServerRequest;
import com.wakarkhan.deliverydropbeta.models.ServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
/**
 * Created by HP on 10-09-2016.
 */
public interface RequestInterface {

    @POST("delivery-drop-beta/")
    Call<ServerResponse> operation(@Body ServerRequest request);

}

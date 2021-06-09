package com.justcode.picklotto.data.repository.api;

import com.google.gson.JsonObject;
import com.justcode.picklotto.data.constant.ApiUrl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitInterface {

    /*
    @Headers({"Accept: application/json"})
    @GET(ApiUrl.GITHUB_URL)
    Call<JsonArray> getCompanyInfo();
    */

    @Headers({"Accept: application/json"})
    @GET(ApiUrl.API_GET_DRW)
    Call<JsonObject> getDrw(@Query("method") String str, @Query("drwNo") int drwNo);

    //--------------------------------------

    /*
    @Headers({"Accept: application/json"})
    @POST(ApiUrl.API_LOGGER_SIGNAL)
    Call<JsonObject> setDeviceSignal(@Body JsonObject obj);

    @Multipart
    @POST(ApiUrl.V2_API_HANDOUT)
    Call<JsonObject> uploadImages(@PartMap Map<String, String> map, @Part ArrayList<MultipartBody.Part> files);
    */
}

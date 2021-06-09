package com.justcode.picklotto.data.repository.api;

import com.justcode.picklotto.data.constant.ApiUrl;
import com.justcode.picklotto.util.NullOnEmptyConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    /**
     * api 호출
     */
    public static RetrofitInterface getRetrofitService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.API_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit.create(RetrofitInterface.class);
    }

    /**
     * 기본 api 호출
     */
    /*public static RetrofitInterface getRetrofitService(String token, String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Authorization", token).build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        if (isGS) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getUnsafeOkHttpClient().build())// 알 수 없는 인증 기관 SSL 무시
                    .build();
        }
        return retrofit.create(RetrofitInterface.class);
    }*/
}

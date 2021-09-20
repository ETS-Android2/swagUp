package com.moringaschool.swagup;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://kohls.p.rapidapi.com/";

    public RetrofitClient() throws IOException {
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

//    OkHttpClient client = new OkHttpClient();
//
//    Request request = new Request.Builder()
//            .url("https://kohls.p.rapidapi.com/auto-complete?query=casual")
//            .get()
//            .addHeader("x-rapidapi-host", "kohls.p.rapidapi.com")
//            .addHeader("x-rapidapi-key", "cddd8f1477msh9b90daf95830852p13e309jsnfd9bf5003a77")
//            .build();
//
//    Response response = client.newCall(request).execute();
}

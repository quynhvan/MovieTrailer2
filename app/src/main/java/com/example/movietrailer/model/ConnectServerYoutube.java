//package com.example.movietrailer.model;
//
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ConnectServerYoutube {
//    private static Retrofit retrofit = null;
//    private static final String BASE_URL ="https://www.googleapis.com/youtube/v3/";
//
//    public static ApiServiceMovie getApiServerYoutube() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit.create(ApiServiceMovie.class);
//    }
//}

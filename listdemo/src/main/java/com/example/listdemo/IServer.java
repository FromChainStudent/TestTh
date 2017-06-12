package com.example.listdemo;

import com.example.listdemo.bean.Data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by YC on 2017/6/5.
 *
 */

public interface IServer {
    @GET("data.json")
    Call<Data> get();
}

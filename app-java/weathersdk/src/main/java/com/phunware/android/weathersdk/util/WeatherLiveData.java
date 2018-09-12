package com.phunware.android.weathersdk.util;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherLiveData<T> extends LiveData<T> implements Callback<T> {
    private Call<T> call;

    public WeatherLiveData(Call<T> call) {
        this.call = call;
    }

    @Override
    protected void onActive() {
        if (!call.isCanceled() && !call.isExecuted()) call.enqueue(this);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        setValue(response.body());
    }

    public void cancel() {
        if (!call.isCanceled()) {
            call.cancel();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        //not implemented
    }
}

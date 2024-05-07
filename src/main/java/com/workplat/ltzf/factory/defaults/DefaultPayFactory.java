package com.workplat.ltzf.factory.defaults;

import com.workplat.ltzf.factory.Configuration;
import com.workplat.ltzf.factory.PayFactory;
import com.workplat.ltzf.payment.nativepay.INativePayApi;
import com.workplat.ltzf.payment.nativepay.NativePayService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Odin
 * @Date: 2024/5/6 21:02
 * @Description:
 */
public class DefaultPayFactory implements PayFactory {

    private final Configuration configuration;

    private final OkHttpClient httpClient;

    public DefaultPayFactory(Configuration configuration) {
        this.configuration = configuration;
        // 日志配置
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(configuration.getLevel());

        // 开启http客户端
        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(configuration.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(configuration.getReadTimeout(), TimeUnit.SECONDS)
                .build();
    }

    @Override
    public NativePayService nativePayService() {
        // 构建API
        INativePayApi nativePayApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(INativePayApi.class);
        return new NativePayService(configuration, nativePayApi);
    }

}

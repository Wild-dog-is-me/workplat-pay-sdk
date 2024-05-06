package com.workplat.ltzf;

import com.alibaba.fastjson.JSON;
import com.workplat.ltzf.payment.nativepay.INativePayApi;
import com.workplat.ltzf.payment.nativepay.model.PrepayResponse;
import com.workplat.ltzf.util.SignUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ApiTest {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() / 1000);

        long timeStamp = System.currentTimeMillis() / 1000;

        Map<String, String> dataMap = new HashMap() {{
            put("mch_id", "1676056677");
            put("out_trade_no", "captain20240506");
            put("total_fee", "0.01");
            put("body", "QQ公仔");
            put("timestamp", String.valueOf(timeStamp));
            put("notify_url", "http://dev.code-farmer.online");
        }};

        System.out.println(SignUtils.createSign(dataMap, "b50a9804007447c86ab6bbb828fb7ef7"));
    }

    @Test
    public void test_retrofit2() throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        INativePayApi nativePayApi = new Retrofit.Builder()
                .baseUrl("https://api.ltzf.cn/")
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(INativePayApi.class);

        long timeStamp = System.currentTimeMillis() / 1000;
        Map<String, String> dataMap = new HashMap() {{
            put("mch_id", "1676056677");
            put("out_trade_no", "captain20240506");
            put("total_fee", "0.01");
            put("body", "QQ公仔");
            put("timestamp", String.valueOf(timeStamp));
            put("notify_url", "http://dev.code-farmer.online");
        }};

        Call<PrepayResponse> call = nativePayApi.prepay(
                dataMap.get("mch_id"),
                dataMap.get("out_trade_no"),
                dataMap.get("total_fee"),
                dataMap.get("body"),
                dataMap.get("timestamp"),
                dataMap.get("notify_url"),
                SignUtils.createSign(dataMap, "b50a9804007447c86ab6bbb828fb7ef7")
        );

        Response<PrepayResponse> response = call.execute();

        log.info("测试结果:{}", JSON.toJSONString(response.body()));
    }

}

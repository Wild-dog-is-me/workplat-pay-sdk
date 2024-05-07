package com.workplat.ltzf.payment.nativepay;

import com.workplat.ltzf.factory.Configuration;
import com.workplat.ltzf.payment.nativepay.model.PrepayRequest;
import com.workplat.ltzf.payment.nativepay.model.PrepayResponse;
import retrofit2.Call;

import java.io.IOException;

/**
 * @Author: Odin
 * @Date: 2024/5/6 20:50
 * @Description:
 */
public class NativePayService {

    private final Configuration configuration;

    private final INativePayApi nativePayApi;

    public NativePayService(Configuration configuration, INativePayApi nativePayApi) {
        this.configuration = configuration;
        this.nativePayApi = nativePayApi;
    }

    public PrepayResponse prepay(PrepayRequest request) throws IOException {
        // 请求接口+签名
        Call<PrepayResponse> prepay = nativePayApi.prepay(request.getMchid(),
                request.getOutTradeNo(),
                request.getTotalFee(),
                request.getBody(),
                request.getTimestamp(),
                request.getNotifyUrl(),
                request.createSign(configuration.getPartnerKey()));

        return prepay.execute().body();
    }
}

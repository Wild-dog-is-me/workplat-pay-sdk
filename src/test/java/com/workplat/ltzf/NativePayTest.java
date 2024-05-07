package com.workplat.ltzf;

import com.alibaba.fastjson.JSON;
import com.workplat.ltzf.factory.Configuration;
import com.workplat.ltzf.factory.defaults.DefaultPayFactory;
import com.workplat.ltzf.payment.nativepay.NativePayService;
import com.workplat.ltzf.payment.nativepay.model.PrepayRequest;
import com.workplat.ltzf.payment.nativepay.model.PrepayResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: Odin
 * @Date: 2024/5/7 08:36
 * @Description:
 */

@Slf4j
public class NativePayTest {

    private NativePayService nativePayService;

    @Before
    public void init() {
        Configuration configuration = new Configuration(
                "1107235", "1676056677", "b50a9804007447c86ab6bbb828fb7ef7"
        );

        DefaultPayFactory payFactory = new DefaultPayFactory(configuration);
        this.nativePayService = payFactory.nativePayService();
    }

    @Test
    public void test_prepay() throws IOException {
        PrepayRequest request = new PrepayRequest();
        request.setMchid("1676056677");
        request.setOutTradeNo(RandomStringUtils.randomNumeric(8));
        request.setTotalFee("0.01");
        request.setBody("测试商品");
        request.setNotifyUrl("http://dev.code-farmer.online");
        PrepayResponse response = nativePayService.prepay(request);

        log.info("native-pay-request:{}", JSON.toJSONString(request));
        log.info("native-pay-response:{}", JSON.toJSONString(response));

    }


}

package com.workplat.ltzf;

import com.workplat.ltzf.util.SignUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

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

}

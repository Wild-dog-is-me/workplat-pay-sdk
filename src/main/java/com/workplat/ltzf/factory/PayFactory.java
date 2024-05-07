package com.workplat.ltzf.factory;

import com.workplat.ltzf.payment.nativepay.NativePayService;

/**
 * @Author: Odin
 * @Date: 2024/5/6 21:00
 * @Description:
 */
public interface PayFactory {

    NativePayService nativePayService();



}

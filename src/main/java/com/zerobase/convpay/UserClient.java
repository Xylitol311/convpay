package com.zerobase.convpay;

import com.zerobase.convpay.config.ApplicationConfig;
import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelReseponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.service.ConveniencePayService;
import com.zerobase.convpay.type.PayMethodType;

import static com.zerobase.convpay.type.ConvenienceType.G25;

public class UserClient {
    public static void main(String[] args) {
        // '사용자' -> 편결이 -> 머니
        ApplicationConfig applicationConfig = new ApplicationConfig();
        ConveniencePayService conveniencePayService = applicationConfig.conveniencePayServiceDiscountPayMethod();
        
        // 결제 1000원, G25
        PayRequest payRequest = new PayRequest(PayMethodType.CARD, G25, 50);
        PayResponse payResponse = conveniencePayService.pay(payRequest);
        
        System.out.println(payResponse);
        
        // G25, 취소 500원
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, G25, 500);
        PayCancelReseponse payCancelReseponse = conveniencePayService.payCancel(payCancelRequest);
        
        System.out.println(payCancelReseponse);
    }
}

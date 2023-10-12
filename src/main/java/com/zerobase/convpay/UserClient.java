package com.zerobase.convpay;

import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelReseponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.service.ConveniencePayService;
import com.zerobase.convpay.type.ConvenienceType;

import static com.zerobase.convpay.type.ConvenienceType.G25;

public class UserClient {
    public static void main(String[] args) {
        // '사용자' -> 편결이 -> 머니
        
        ConveniencePayService conveniencePayService = new ConveniencePayService();
        
        // 결제 1000원, G25
        PayRequest payRequest = new PayRequest(G25, 1000);
        PayResponse payResponse = conveniencePayService.pay(payRequest);
        
        System.out.println(payResponse);
        
        // G25, 취소 500원
        PayCancelRequest payCancelRequest = new PayCancelRequest(G25, 500);
        PayCancelReseponse payCancelReseponse = conveniencePayService.payCancel(payCancelRequest);
        
        System.out.println(payCancelReseponse);
    }
}

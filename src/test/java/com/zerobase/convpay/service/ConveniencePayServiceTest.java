package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.ConvenienceType;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.dto.PayResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConveniencePayServiceTest {
    ConveniencePayService conveniencePayService = new ConveniencePayService();
    @Test
    void paySuccess() {
        //given : 어떤 데이터가 있을 때
        PayRequest payRequest = new PayRequest(ConvenienceType.G25, 150);
        //when : 어떤 동작을 하면?
        PayResponse payResponse = conveniencePayService.pay(payRequest);
        //then : 어떤 결과가 나온다.
        assertEquals(PayResult.SUCCESS, payResponse.getPayResult());
        assertEquals(150, payResponse.getPaidAmount());
        
    }
}
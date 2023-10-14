package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelReseponse;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.type.PayCancelResult;
import com.zerobase.convpay.type.PayMethodType;
import com.zerobase.convpay.type.PayResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConveniencePayServiceTest {
    ConveniencePayService conveniencePayService = new ConveniencePayService();
    @Test
    void paySuccess() {
        //given : 어떤 데이터가 있을 때
        PayRequest payRequest = new PayRequest(PayMethodType.CARD, ConvenienceType.G25, 10000);
        //when : 어떤 동작을 하면?
        PayResponse payResponse = conveniencePayService.pay(payRequest);
        //then : 어떤 결과가 나온다.
        assertEquals(PayResult.SUCCESS, payResponse.getPayResult());
        assertEquals(10000, payResponse.getPaidAmount());
    }
    
    @Test
    void payFail() {
        //given : 어떤 데이터가 있을 때
        PayRequest payRequest = new PayRequest(PayMethodType.CARD, ConvenienceType.G25, 1000001);
        //when : 어떤 동작을 하면?
        PayResponse payResponse = conveniencePayService.pay(payRequest);
        //then : 어떤 결과가 나온다.
        assertEquals(PayResult.FAIL, payResponse.getPayResult());
        assertEquals(0, payResponse.getPaidAmount());
    }
    
    @Test
    void payCancelSuccess() {
        //given : 어떤 데이터가 있을 때
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.CARD, ConvenienceType.G25, 500);
        //when : 어떤 동작을 하면?
        PayCancelReseponse payCancelReseponse = conveniencePayService.payCancel(payCancelRequest);
        //then : 어떤 결과가 나온다.
        assertEquals(PayCancelResult.PAY_CANCEL_FAIL, payCancelReseponse.getPayCancelResult());
        assertEquals(0, payCancelReseponse.getPayCanceledAmount());
    }
    
    @Test
    void payCancelFail() {
        //given : 어떤 데이터가 있을 때
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.CARD, ConvenienceType.G25, 50);
        //when : 어떤 동작을 하면?
        PayCancelReseponse payCancelReseponse = conveniencePayService.payCancel(payCancelRequest);
        //then : 어떤 결과가 나온다.
        assertEquals(PayCancelResult.PAY_CANCEL_FAIL, payCancelReseponse.getPayCancelResult());
        assertEquals(0, payCancelReseponse.getPayCanceledAmount());
    }
}
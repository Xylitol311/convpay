package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.type.PayMethodType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountByPayMethodTest {
    DiscountByPayMethod discountByPayMethod = new DiscountByPayMethod();
    
    @Test
    void setDiscountByPayMethod() {
        //given
        PayRequest payRequestMoney = new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 1000);
        PayRequest payRequestCard = new PayRequest(PayMethodType.CARD, ConvenienceType.G25, 1000);
        
        //when
        Integer discountAmountMoney = discountByPayMethod.getDiscountedAmount(payRequestMoney);
        Integer discountAmountCard = discountByPayMethod.getDiscountedAmount(payRequestCard);
        
        //then
        assertEquals(700, discountAmountMoney);
        assertEquals(1000, discountAmountCard);
    }
    
}
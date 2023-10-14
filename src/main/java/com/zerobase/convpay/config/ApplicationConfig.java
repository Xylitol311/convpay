package com.zerobase.convpay.config;

import com.zerobase.convpay.service.*;
import com.zerobase.convpay.service.adapter.CardAdapter;
import com.zerobase.convpay.service.adapter.MoneyAdapter;

import java.util.Arrays;
import java.util.HashSet;

public class ApplicationConfig {
    public ConveniencePayService conveniencePayServiceDiscountPayMethod() {
        return new ConveniencePayService(
                new HashSet<>(
                        Arrays.asList(new MoneyAdapter(), new CardAdapter())
                ),
                new DiscountByPayMethod()
        );
    }
    
    public ConveniencePayService conveniencePayServiceDiscountConvenience() {
        return new ConveniencePayService(
                new HashSet<>(
                        Arrays.asList(new MoneyAdapter(), new CardAdapter())
                ),
                new DiscountByConvenience()
        );
    }
}

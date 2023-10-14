package com.zerobase.convpay.config;

import com.zerobase.convpay.service.*;
import com.zerobase.convpay.service.adapter.CardAdapter;
import com.zerobase.convpay.service.adapter.MoneyAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class ApplicationConfig {
    @Bean
    public ConveniencePayService conveniencePayService() {
        return new ConveniencePayService(
                new HashSet<>(
                        Arrays.asList(moneyAdapter(), getCardAdapter())
                ),
                discountByConvenience()
        );
    }
    
    @Bean
    public CardAdapter getCardAdapter() {
        return new CardAdapter();
    }
    
    @Bean
    public MoneyAdapter moneyAdapter() {
        return new MoneyAdapter();
    }
    
    @Bean
    public DiscountByConvenience discountByConvenience() {
        return new DiscountByConvenience();
    }
}

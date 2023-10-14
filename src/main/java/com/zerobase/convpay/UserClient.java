package com.zerobase.convpay;

import com.zerobase.convpay.config.ApplicationConfig;
import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelReseponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.service.ConveniencePayService;
import com.zerobase.convpay.type.PayMethodType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static com.zerobase.convpay.type.ConvenienceType.G25;

public class UserClient {
    public static void main(String[] args) {
        // '사용자' -> 편결이 -> 머니
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        
        ConveniencePayService conveniencePayService = applicationContext.getBean("conveniencePayService", ConveniencePayService.class);
        
        // 결제 1000원, G25
        System.out.println("---결제---");
        PayRequest payRequest = new PayRequest(PayMethodType.CARD, G25, 50);
        PayResponse payResponse = conveniencePayService.pay(payRequest);
        
        System.out.println(payResponse);
        System.out.println();
        
        // G25, 취소 500원
        System.out.println("---결제 취소---");
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, G25, 500);
        PayCancelReseponse payCancelReseponse = conveniencePayService.payCancel(payCancelRequest);
        
        System.out.println(payCancelReseponse);
    }
}

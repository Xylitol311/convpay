package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;
import com.zerobase.convpay.type.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.zerobase.convpay.type.MoneyUseResult.USE_FAIL;
import static com.zerobase.convpay.type.PayCancelResult.PAY_CANCEL_SUCCESS;

public class ConveniencePayService {
    private final Map<PayMethodType, PaymentInterface> paymentInterfaceMap = new HashMap<>();
    private final DiscountInterface discountInterface;
    
    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet, DiscountInterface discountInterface) {
        paymentInterfaceSet.forEach(
                paymentInterface -> paymentInterfaceMap.put(
                        paymentInterface.getPayMethodType(),
                        paymentInterface
                )
        );
        
        this.discountInterface = discountInterface;
    }
    
    public PayResponse pay(PayRequest payRequest) {
        PaymentInterface paymentInterface =
                paymentInterfaceMap.get(payRequest.getPayMethodType());
        
        Integer discountedAmount = discountInterface.getDiscountedAmount(payRequest);
        PaymentResult paymentResult = paymentInterface.payment(discountedAmount);
        
        // Fail Case
        if (paymentResult == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }
        
        // Success Case
        return new PayResponse(PayResult.SUCCESS, discountedAmount);
        
    }
    
    public PayCancelReseponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface =
                paymentInterfaceMap.get(payCancelRequest.getPayMethodType());
        
        CancelPaymentResult cancelPaymentResult =
                paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());
        
        
        if (cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL) {
            return new PayCancelReseponse(PayCancelResult.PAY_CANCEL_FAIL, 0);
        }
        //success case
        return new PayCancelReseponse(PAY_CANCEL_SUCCESS, payCancelRequest.getPayCancelAmount());
        
    }
}

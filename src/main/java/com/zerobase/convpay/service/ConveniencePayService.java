package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;
import com.zerobase.convpay.type.*;

import static com.zerobase.convpay.type.MoneyUseResult.USE_FAIL;
import static com.zerobase.convpay.type.PayCancelResult.PAY_CANCEL_SUCCESS;

public class ConveniencePayService {
    private final MoneyAdapter moneyAdapter = new MoneyAdapter();
    private final CardAdapter cardAdapter = new CardAdapter();
    
    public PayResponse pay(PayRequest payRequest) {
        PaymentInterface paymentInterface;
        
        if (payRequest.getPayMethodType() == PayMethodType.CARD) {
            paymentInterface = cardAdapter;
        } else {
            paymentInterface = moneyAdapter;
        }
        
        PaymentResult paymentResult = paymentInterface.payment(payRequest.getPayAmount());
        
        // Fail Case
        if (paymentResult == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }
        
        // Success Case
        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());
        
    }
    
    public PayCancelReseponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface;
        
        if (payCancelRequest.getPayMethodType() == PayMethodType.CARD) {
            paymentInterface = cardAdapter;
        } else {
            paymentInterface = moneyAdapter;
        }
        
        CancelPaymentResult cancelPaymentResult = paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());
        
        
        if (cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL) {
            return new PayCancelReseponse(PayCancelResult.PAY_CANCEL_FAIL, 0);
        }
        //success case
        return new PayCancelReseponse(PAY_CANCEL_SUCCESS, payCancelRequest.getPayCancelAmount());
        
    }
}

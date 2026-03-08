package org.appsdeveloperblog.saga.payments.service;

import org.appsdeveloperblog.saga.core.dto.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();

    Payment process(Payment payment);
}

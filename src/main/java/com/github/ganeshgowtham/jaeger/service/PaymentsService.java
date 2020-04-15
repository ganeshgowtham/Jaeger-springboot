package com.github.ganeshgowtham.jaeger.service;

import com.github.ganeshgowtham.jaeger.model.Payment;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentsService {

    private final List<Payment> allPayments = new ArrayList<>();
    @Autowired
    private Tracer tracer;

    public void addPayment(Payment payment, Span rootSpan) {
        if (rootSpan != null) {
            Span span = tracer.buildSpan("service get payment").asChildOf(rootSpan).start();
            allPayments.add(payment);
            span.setTag("uetr", payment.getUetr());
            span.finish();
        } else {
            allPayments.add(payment);
        }
    }

    public List<Payment> getAllPayments(Span rootSpan) {
        Span span = tracer.buildSpan("service get payment").asChildOf(rootSpan).start();
        // processing
        try {
            Thread.sleep(2000);
            span.setTag("sleeping.delay", 2000);
        } catch (Exception e) {
        } finally {
            span.finish();
        }
        return allPayments;
    }
}

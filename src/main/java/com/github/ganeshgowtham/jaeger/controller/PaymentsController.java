package com.github.ganeshgowtham.jaeger.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.ganeshgowtham.jaeger.model.MessageFormat;
import com.github.ganeshgowtham.jaeger.model.Payment;
import com.github.ganeshgowtham.jaeger.service.PaymentsService;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.UUID;

@Slf4j
@RestController
@Api(value = "Payments API")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @Autowired
    private Tracer tracer;

    @PostConstruct
    public void init() {
        paymentsService.addPayment(
                Payment.builder().uetr(UUID.randomUUID().toString()).
                        bearerAccountNo("ac1").bearerName("BillGates").
                        beneficiaryAccountNo("acc1").beneficiaryName("Satya").format(MessageFormat.ACH).build(),null);

        paymentsService.addPayment(
                Payment.builder().uetr(UUID.randomUUID().toString()).
                        bearerAccountNo("ac2").bearerName("JackMa").
                        beneficiaryAccountNo("acc2").beneficiaryName("Wohai").format(MessageFormat.MTC).build(),null);

        paymentsService.addPayment(
                Payment.builder().uetr(UUID.randomUUID().toString()).
                        bearerAccountNo("ac3").bearerName("Narayan").
                        beneficiaryAccountNo("acc3").beneficiaryName("Moorthy").format(MessageFormat.PAIN).build(),null);
log.info("Added dummy payments {}", paymentsService.getAllPayments(null).size());
    }

    @ApiOperation(value = "Get All Payments ", response = ResponseEntity.class)
    @RequestMapping(value = "/api/payments", method = RequestMethod.GET)
    public ResponseEntity getAllPayments() {
        Span span = tracer.buildSpan("get all payments").start();
        log.info("Receive Request to Get All payments");
        Collection<Payment> payments = paymentsService.getAllPayments(span);
        span.finish();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @ApiOperation(value = "Create Payment ", response = ResponseEntity.class)
    @RequestMapping(value = "/api/payment", method = RequestMethod.POST)
    public ResponseEntity createPayment(@RequestBody Payment payment) {
        Span span = tracer.buildSpan("create payment").start();
        log.info("Receive Request to add payment {}", payment);
        payment.setUetr(UUID.randomUUID().toString());
        paymentsService.addPayment(payment,span);
        span.setTag("http.status_code", 200);
        span.finish();
        return new ResponseEntity(payment, HttpStatus.OK);
    }
}

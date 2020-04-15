package com.github.ganeshgowtham.jaeger.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private MessageFormat format;
    private String bearerName;
    private String bearerAccountNo;
    private String beneficiaryName;
    private String beneficiaryAccountNo;
    private String uetr;
}

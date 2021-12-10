package com.ash.test.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class VendorRequest {
    private String name;

    private String displayName;

    private String description;

    private String customerId;

    private double multiplier;
}

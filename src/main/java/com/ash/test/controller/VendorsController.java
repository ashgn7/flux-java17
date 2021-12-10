package com.ash.test.controller;

import com.ash.test.model.Vendor;
import com.ash.test.model.request.VendorRequest;
import com.ash.test.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController("/vendors/v1")
@Slf4j
public class VendorsController {

    @Autowired
    VendorService vendorService;

    @GetMapping(value = "/list_vendors", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Vendor> getAllVendors() {
        log.info("Getting all existing vendors ");
        var allVendors = vendorService.getAllVendors();
        return allVendors;
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Mono<Vendor>> findByVendorId(@PathVariable("id") String id) {
        log.info("findByVendorId ==>>> {}", id);
        Mono<Vendor> vendorMono = Mono.empty();
        if (ObjectId.isValid(id)) {
            var objectId = new ObjectId(id);
            vendorMono = vendorService.findByVendorId(objectId);
        }
        return new ResponseEntity<>(vendorMono, vendorMono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create_vendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createVendor(@RequestBody VendorRequest vendorRequest) {
        log.info("Creating vendor with request {} at {}", vendorRequest, LocalDateTime.now());
        vendorService.addVendor(vendorRequest);
        log.info("Vendor saved successfully");
    }

    @PostMapping(value = "/create_multi_vendors/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createVendors(@PathVariable("count") Integer count) {
        log.info("Creating {} vendors at {}", count, LocalDateTime.now());
        vendorService.addVendors(count);
        log.info("Vendor saved successfully");
    }

}

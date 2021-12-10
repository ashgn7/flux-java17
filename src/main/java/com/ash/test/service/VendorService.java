package com.ash.test.service;

import com.ash.test.model.Vendor;
import com.ash.test.model.request.VendorRequest;
import com.ash.test.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Slf4j
public class VendorService {

    @Autowired
    VendorRepository vendorRepository;

    public Flux<Vendor> getAllVendors() {
        log.info("Getting all vendors");
        return vendorRepository.findAll();
    }


    public Mono<Vendor> findByVendorId(ObjectId id) {
        return vendorRepository.findById(id);
    }

    public void addVendor(VendorRequest vendorRequest) {
        Vendor vendor = Vendor.builder().name(vendorRequest.getName()).displayName(vendorRequest.getDisplayName())
                .description(vendorRequest.getDescription()).
                id(ObjectId.get().toString()).build();
        log.info("Adding vendor to DB ==>> {}", vendor);
        vendorRepository.save(vendor).subscribe();
    }

    public void addVendors(Integer count) {
        for (int i = 0; i < count; i++) {
            Vendor vendor = Vendor.builder().name(vendorRequest.getName()).displayName(vendorRequest.getDisplayName())
                    .description(vendorRequest.getDescription()).
                    id(ObjectId.get().toString()).build();
        }
        vendorRepository.
        log.info("Adding vendor to DB ==>> {}", vendor);
    }
}

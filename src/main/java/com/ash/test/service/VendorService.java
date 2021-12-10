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

import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        var vendor = Vendor.builder().name(vendorRequest.getName()).
                displayName(vendorRequest.getDisplayName()).
                description(vendorRequest.getDescription()).
                id(ObjectId.get().toString()).build();
        log.info("Adding vendor to DB ==>> {}", vendor);
        vendorRepository.save(vendor).subscribe();
    }

    public void addVendors(Integer count) {
        var vendorList = IntStream.range(0, count).
                mapToObj(i -> Vendor.builder().name("vendorName " + i).
                        displayName("vendorName " + i).
                        description("vendorDesc " + i).
                        multiplier(i * 3).
                        customerId("Id " + i).
                id(ObjectId.get().toString()).build()).collect(Collectors.toList());
        log.info("Added {} vendors to DB ", count);
        vendorRepository.saveAll(vendorList).subscribe();
    }
}

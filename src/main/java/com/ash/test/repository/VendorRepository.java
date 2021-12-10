package com.ash.test.repository;

import com.ash.test.model.Vendor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends ReactiveMongoRepository<Vendor, ObjectId> {
}

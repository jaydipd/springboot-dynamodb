package org.aws.demo.data.repositories;

import org.aws.demo.data.model.OpenApi;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface OpenApiRepository extends CrudRepository<OpenApi, String> {
    Optional<OpenApi> findById(String id);
}

package org.aws.demo.repositories;

import org.aws.demo.data.model.OpenApi;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomRepo {
    List<OpenApi> findByNameWithFilters(String name, Map<String, ?> filterData);
}

package org.aws.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.aws.demo.data.dtos.OpenApiDTO;
import org.aws.demo.data.model.OpenApi;
import org.aws.demo.repositories.OpenApiRepository;
import org.aws.demo.exceptionpkg.DataNotFoundException;
import org.aws.demo.utility.OpenApiObjectConversionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OpenApiController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OpenApiRepository openApiRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${open.api.uri}")
    private String openApiUri;

    @PostMapping(value = "/")
    public ResponseEntity<String> create(@RequestBody OpenApiDTO openApiDTO) throws Exception {

        OpenApi openApi = openApiRepository.save(OpenApiObjectConversionUtility.openApiDtoToEntity(openApiDTO));
        return ResponseEntity.ok(objectMapper.writeValueAsString(openApi));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpenApi> findById(@PathVariable(name = "id") String id) throws Exception {
        Optional<OpenApi> openApi = openApiRepository.findById(id);
        if (!openApi.isPresent()) {
            throw new DataNotFoundException("Data not found: " + id);
        }
        return new ResponseEntity<>(openApi.get(), HttpStatus.OK);
    }

    @GetMapping("/")
    public String findAll() throws Exception {
        List<OpenApi> openApi = (List<OpenApi>) openApiRepository.findAll();
        return objectMapper.writeValueAsString(openApi);
    }


    @GetMapping("/city/{city}/{apiId}")
    public String getDataFromOpenWeatherApi(@PathVariable(name = "city") String city, @PathVariable(name = "apiId") String apiId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(openApiUri)
                .queryParam("q", city)
                .queryParam("appid", apiId);
        ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);
        if (!(response.getStatusCode() == HttpStatus.OK)) {
            throw new DataNotFoundException("problem fetching data: " + city);
        }
        return response.getBody();
    }

    @GetMapping("/findByName/{name}")
    public List<OpenApi> findByName(@PathVariable(name = "name") String name) {
        Optional<List<OpenApi>> optionalListOfOpenApi = openApiRepository.findByName(name);
        if (!optionalListOfOpenApi.isPresent()) {
            throw new DataNotFoundException("Data not found for: " + name);
        }
        return optionalListOfOpenApi.get();
    }


}

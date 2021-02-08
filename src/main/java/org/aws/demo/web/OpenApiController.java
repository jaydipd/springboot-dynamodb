package org.aws.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aws.demo.data.dtos.OpenApiDTO;
import org.aws.demo.data.model.OpenApi;
import org.aws.demo.data.repositories.OpenApiRepository;
import org.aws.demo.utility.OpenApiObjectConversionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
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
    public String findById(@PathVariable(name = "id") String id) throws Exception {
        System.out.println("id: " + id);
        Optional<OpenApi> openApi = openApiRepository.findById(id);
        if (!openApi.isPresent()) {
            throw new RuntimeException("Data not found: " + id);
        }
        return objectMapper.writeValueAsString(openApi.get());
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
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return "failed to get data from open weather api";
    }

}

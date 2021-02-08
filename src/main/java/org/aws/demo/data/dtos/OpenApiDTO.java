package org.aws.demo.data.dtos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class OpenApiDTO {

    private String id;

    private CoordDTO coord;

    private List<WeatherDTO> weather;

    private String base;

    private MainDTO main;

    private Integer visibility;

    private WindDTO wind;

    private CloudDTO clouds;

    private double dt;

    private SysDTO sys;

    private Double timezone;

    private String name;
    private Double cod;


}

package org.aws.demo.data.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.aws.demo.utility.IdGenrationConverter;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "TB_OPEN_API")
public class OpenApi {

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBTypeConverted(converter = IdGenrationConverter.class)
    private String id;

    private Coord coord;

    @DynamoDBAttribute(attributeName = "weather")
    private List<Weather> weather;

    @DynamoDBAttribute(attributeName = "base")
    private String base;

    @DynamoDBAttribute(attributeName = "main")
    private Main main;

    private Integer visibility;

    private Wind wind;

    private Cloud clouds;

    private double dt;

    private Sys sys;

    private Double timezone;

    private String name;
    private Double cod;


}

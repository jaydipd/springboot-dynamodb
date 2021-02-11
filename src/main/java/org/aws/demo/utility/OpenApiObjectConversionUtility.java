package org.aws.demo.utility;

import org.aws.demo.data.dtos.*;
import org.aws.demo.data.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class OpenApiObjectConversionUtility {
    public static OpenApi openApiDtoToEntity(OpenApiDTO openApiDTO) {
        return OpenApi.builder()
                .base(openApiDTO.getBase())
                .clouds(getCloudInstance(openApiDTO.getClouds()))
                .cod(openApiDTO.getCod())
                .dt(openApiDTO.getDt())
                .id(openApiDTO.getId())
                .coord(getCoordInstance(openApiDTO.getCoord()))
                .main(getMainInstance(openApiDTO.getMain()))
                .name(openApiDTO.getName())
                .sys(getSysInstance(openApiDTO.getSys()))
                .timezone(openApiDTO.getTimezone())
                .visibility(openApiDTO.getVisibility())
                .weather(getWeatherList(openApiDTO.getWeather()))
                .wind(getWindInstance(openApiDTO.getWind()))
                .build();
    }


    public static Wind getWindInstance(final WindDTO windDTO) {
        return Wind.builder()
                .deg(windDTO.getDeg())
                .speed(windDTO.getSpeed()).build();
    }

    public static Weather getWeatherInstance(final WeatherDTO weatherDTO) {
        return Weather.builder().id(weatherDTO.getId())
                .description(weatherDTO.getDescription())
                .icon(weatherDTO.getIcon())
                .main(weatherDTO.getMain())
                .build();
    }

    public static List<Weather> getWeatherList(final List<WeatherDTO> weatherDTOS) {
        return weatherDTOS.parallelStream().map(OpenApiObjectConversionUtility::getWeatherInstance).collect(Collectors.toList());
    }

    public static Sys getSysInstance(final SysDTO sysDTO) {
        return Sys.builder().country(sysDTO.getCountry())
                .sunrise(sysDTO.getSunrise())
                .sunset(sysDTO.getSunset())
                .build();
    }

    public static Main getMainInstance(final MainDTO mainDTO) {
        return Main.builder().feels_like(mainDTO.getFeels_like())
                .grnd_level(mainDTO.getGrnd_level())
                .humidity(mainDTO.getHumidity())
                .pressure(mainDTO.getPressure())
                .sea_level(mainDTO.getSea_level())
                .temp(mainDTO.getTemp())
                .temp_max(mainDTO.getTemp_max())
                .temp_min(mainDTO.getTemp_min()).build();
    }

    public static Coord getCoordInstance(final CoordDTO coordDTO) {
        return Coord.builder().lat(coordDTO.getLat())
                .lon(coordDTO.getLon()).build();
    }

    public static Cloud getCloudInstance(final CloudDTO cloudDTO) {
        return Cloud.builder().all(cloudDTO.getAll()).build();
    }
}

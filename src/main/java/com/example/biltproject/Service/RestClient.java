package com.example.biltproject.Service;

import com.example.biltproject.Objects.AirlineResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//TODO: Config and URL can come from  property files. Properties can be loaded with Java Profiles.
@FeignClient(value = "feign", url = "https://api.instantwebtools.net/v1/")
public interface RestClient  {
    @RequestMapping(method = RequestMethod.GET, value = "/airlines/{airlineId}")
    AirlineResponse readAirLineById(@PathVariable("airlineId") String airlineId);

}

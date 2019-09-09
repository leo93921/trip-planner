package it.unisalento.tripplanner.api.rest;

import it.unisalento.tripplanner.dto.Trip;
import it.unisalento.tripplanner.iservice.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@RequestMapping("/api/trip")
@RestController
public class TripRestController {

    private ITripService service;

    public TripRestController(
            @Autowired ITripService service
    ) {
        this.service = service;
    }

    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping
    public Trip saveTrip(@RequestBody Trip trip) {
        return service.saveTrip(trip);
    }


}

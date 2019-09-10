package it.unisalento.tripplanner.api.rest;

import it.unisalento.tripplanner.dto.Trip;
import it.unisalento.tripplanner.iservice.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
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

    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/{page}/{size}")
    public Page<Trip> findAll(
            @PathVariable("page") Integer pageNumber,
            @PathVariable("size") Integer pageSize
    ) {
        return service.findAll(pageNumber, pageSize);
    }

    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping
    public Trip updateTrip(
            @RequestBody Trip trip
    ) {
        return service.updateTrip(trip);
    }

    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/{tripID}")
    public Trip findByID(
            @PathVariable("tripID") String id
    ) {
        return service.findByID(id);
    }

    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/{tripID}")
    public boolean deleteByID(
            @PathVariable("tripID") String id
    ) {
        return service.deleteByID(id);
    }

    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/by-user/{userID}/{page}/{size}")
    public Page<Trip> findByUserID(
            @PathVariable("userID") String userID,
            @PathVariable("page") Integer pageNumber,
            @PathVariable("size") Integer pageSize
    ) {
        return service.findByUserID(userID, pageNumber, pageSize);
    }

}

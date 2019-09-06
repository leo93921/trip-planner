package it.unisalento.tripplanner.api.rest;

import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.iservice.IItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@RestController
@RequestMapping("/api/itinerary")
public class ItineraryRestController {

    private IItineraryService service;

    public ItineraryRestController(@Autowired IItineraryService service) {
        this.service = service;
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{page}/{pageSize}")
    public Page<Itinerary> findAll(
            @PathVariable("page") Integer pageNumber,
            @PathVariable("pageSize") Integer pageSize
    ) {
        return service.findAll(pageNumber, pageSize);
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{id}")
    public Itinerary findById(@PathVariable("id") String id) {
        return service.findByID(id);
    }

    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    public Itinerary save(
            @RequestBody Itinerary itinerary
    ) {
        return service.save(itinerary);
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping("/{id}")
    public boolean deleteItinerary(
            @PathVariable("id") String id
    ) {
        return service.deleteByID(id);
    }

    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @PutMapping
    public Itinerary update(
            @RequestBody Itinerary itinerary
    ) {
        return service.update(itinerary);
    }

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/by-user/{id}/{page}/{pageSize}")
    public Page<Itinerary> findByUserID(
            @PathVariable("id") String userID,
            @PathVariable("page") Integer pageNumber,
            @PathVariable("pageSize") Integer pageSize
    ) {
        return service.findByUserID(userID, pageNumber, pageSize);
    }

}

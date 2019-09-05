package it.unisalento.tripplanner.api.rest;

import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.iservice.IItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@RestController
@RequestMapping("/api/itinerary")
public class ItineraryRestController {

    @Autowired
    private IItineraryService service;

    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{page}/{pageSize}")
    public Page<Itinerary> findAll(
            @PathVariable("page") Integer pageNumber,
            @PathVariable("pageSize") Integer pageSize
    ) {
        return service.findAll(pageNumber, pageSize);
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

}

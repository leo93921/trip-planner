package it.unisalento.tripplanner.api.rest;

import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.iservice.IItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

package it.unisalento.tripplanner.service;

import it.unisalento.tripplanner.converter.ItineraryConverter;
import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.iservice.IItineraryService;
import it.unisalento.tripplanner.model.ItineraryModel;
import it.unisalento.tripplanner.repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class ItineraryService implements IItineraryService {

    @Autowired
    private ItineraryRepository repository;

    @Override @Transactional(readOnly = true)
    public Page<Itinerary> findAll(Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<ItineraryModel> list = repository.findAll(pageRequest);

        List<Itinerary> itineraries = new ArrayList<>();
        for(ItineraryModel model : list) {
            itineraries.add(ItineraryConverter.INSTANCE.toDto(model));
        }

        // Build new page and return it
        return new PageImpl<>(itineraries, list.getPageable(), list.getTotalElements());
    }
}

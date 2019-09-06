package it.unisalento.tripplanner.service;

import it.unisalento.tripplanner.converter.ItineraryConverter;
import it.unisalento.tripplanner.dto.Itinerary;
import it.unisalento.tripplanner.exception.ItineraryNotFoundException;
import it.unisalento.tripplanner.iservice.IItineraryService;
import it.unisalento.tripplanner.model.ItineraryModel;
import it.unisalento.tripplanner.repository.ItineraryRepository;
import it.unisalento.tripplanner.utils.PageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ItineraryService implements IItineraryService {

    private ItineraryRepository repository;
    private PageBuilder<Itinerary> builder;

    public ItineraryService(
            @Autowired PageBuilder<Itinerary> builder,
            @Autowired ItineraryRepository repository
    ) {
        this.builder = builder;
        this.repository = repository;
    }

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

    /**
     * Find an Itinerary by ID
     * @param id The ID of the Itinerary
     * @return Itinerary found
     * @throws ItineraryNotFoundException if the Itinerary with given ID doesn't exist
     */
    @Override @Transactional(readOnly = true)
    public Itinerary findByID(String id) throws ItineraryNotFoundException {
        Optional<ItineraryModel> model = repository.findById(id);
        ItineraryModel found = model.orElseThrow(ItineraryNotFoundException::new);
        return ItineraryConverter.INSTANCE.toDto(found);
    }

    /**
     * Persist a new Itinerary
     * @param itinerary DTO to be saved
     * @return Saved DTO
     */
    @Override @Transactional
    public Itinerary save(Itinerary itinerary) {
        ItineraryModel model = ItineraryConverter.INSTANCE.toModel(itinerary);
        ItineraryModel saved = repository.save(model);
        return ItineraryConverter.INSTANCE.toDto(saved);
    }


    /**
     * Delete an Itinerary by ID
     * @param id The resource ID
     * @return True if ok
     * @throws ItineraryNotFoundException if the Itinerary with given ID doesn't exist
     */
    @Override @Transactional
    public boolean deleteByID(String id) throws ItineraryNotFoundException {
        Optional<ItineraryModel> optional = repository.findById(id);
        ItineraryModel foundModel = optional.orElseThrow(ItineraryNotFoundException::new);
        repository.delete(foundModel);
        return true;
    }

    /**
     * Update an existing Itinerary
     * @param itinerary The new itinerary
     * @return the saved Itinerary
     */
    @Override @Transactional
    public Itinerary update(Itinerary itinerary) {
        Optional<ItineraryModel> model = repository.findById(itinerary.getId());

        ItineraryModel saved = model.orElseThrow(ItineraryNotFoundException::new);
        ItineraryModel received = ItineraryConverter.INSTANCE.toModel(itinerary);

        // update properties
        saved.setStops(received.getStops());
        saved.setDescription(received.getDescription());
        saved.setBudgetLevel(received.getBudgetLevel());
        saved.setStartPoint(received.getStartPoint());
        saved.setEndPoint(received.getEndPoint());
        saved.setMaxBudget(received.getMaxBudget());
        saved.setTitle(received.getTitle());
        saved.setUserId(received.getUserId());
        saved.setUpdateDate(new Date());

        saved = repository.save(saved);
        return ItineraryConverter.INSTANCE.toDto(saved);
    }

    /**
     * Return page of itineraries for a given user
     * @param userID ID of the user
     * @param pageNumber Page number for pagination
     * @param pageSize Page size for pagination
     * @return Page of itineraries of a given user
     */
    @Override
    public Page<Itinerary> findByUserID(String userID, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<ItineraryModel> userItineraries = repository.findByUserId(userID, pageRequest);

        List<Itinerary> itineraries = new ArrayList<>();
        for (ItineraryModel model : userItineraries)
            itineraries.add(ItineraryConverter.INSTANCE.toDto(model));

        return builder
                .setElements(itineraries)
                .setPage(userItineraries.getPageable())
                .setTotalElements(userItineraries.getNumberOfElements())
                .build();
    }
}

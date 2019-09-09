package it.unisalento.tripplanner.service;

import it.unisalento.tripplanner.converter.TripConverter;
import it.unisalento.tripplanner.dto.Trip;
import it.unisalento.tripplanner.iservice.ITripService;
import it.unisalento.tripplanner.model.TripModel;
import it.unisalento.tripplanner.repository.TripRepository;
import it.unisalento.tripplanner.utils.PageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TripService implements ITripService {

    private TripRepository repository;
    private PageBuilder<Trip> builder;

    public TripService(
            @Autowired TripRepository repository,
            @Autowired PageBuilder<Trip> builder
    ) {
        this.repository = repository;
        this.builder = builder;
    }

    @Transactional
    @Override
    public Trip saveTrip(Trip trip) {
        TripModel model = TripConverter.INSTANCE.toModel(trip);
        model.setCreationDate(new Date());
        TripModel saved = repository.save(model);
        return TripConverter.INSTANCE.toDto(saved);
    }

    @Override
    public Page<Trip> findAll(Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<TripModel> modelPage = repository.findAll(page);

        // Transform into dto list
        List<Trip> trips = new ArrayList<>();
        for (TripModel model : modelPage.getContent()) {
            trips.add(TripConverter.INSTANCE.toDto(model));
        }

        return builder
                .setElements(trips)
                .setTotalElements(modelPage.getTotalElements())
                .setPage(modelPage.getPageable())
                .build();

    }
}

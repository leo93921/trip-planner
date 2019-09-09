package it.unisalento.tripplanner.service;

import it.unisalento.tripplanner.converter.TripConverter;
import it.unisalento.tripplanner.dto.Trip;
import it.unisalento.tripplanner.iservice.ITripService;
import it.unisalento.tripplanner.model.TripModel;
import it.unisalento.tripplanner.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TripService implements ITripService {

    @Autowired
    private TripRepository repository;

    @Transactional
    @Override
    public Trip saveTrip(Trip trip) {
        TripModel model = TripConverter.INSTANCE.toModel(trip);
        model.setCreationDate(new Date());
        TripModel saved = repository.save(model);
        return TripConverter.INSTANCE.toDto(saved);
    }
}

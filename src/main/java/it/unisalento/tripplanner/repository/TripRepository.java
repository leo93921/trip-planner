package it.unisalento.tripplanner.repository;

import it.unisalento.tripplanner.model.TripModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends MongoRepository<TripModel, String> {
}

package it.unisalento.tripplanner.repository;

import it.unisalento.tripplanner.model.ItineraryModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItineraryRepository extends MongoRepository<ItineraryModel, String> {

}

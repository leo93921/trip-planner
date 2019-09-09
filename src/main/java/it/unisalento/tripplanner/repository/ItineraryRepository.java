package it.unisalento.tripplanner.repository;

import it.unisalento.tripplanner.model.ItineraryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends MongoRepository<ItineraryModel, String> {
    Page<ItineraryModel> findByUserId(String userID, Pageable pageRequest);
}

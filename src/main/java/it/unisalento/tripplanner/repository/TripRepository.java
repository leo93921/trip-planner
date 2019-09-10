package it.unisalento.tripplanner.repository;

import it.unisalento.tripplanner.model.TripModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends MongoRepository<TripModel, String> {
    Page<TripModel> findByUserId(String userID, Pageable pageRequest);
}

package com.rental.backend.repository;

import com.rental.backend.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
  boolean existsByPicture(String picture);
}


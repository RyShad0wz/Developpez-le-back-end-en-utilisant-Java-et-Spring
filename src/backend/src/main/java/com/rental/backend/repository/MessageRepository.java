package com.rental.backend.repository;

import com.rental.backend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
  // Ici, on suppose que l'entité Message possède une relation ManyToOne vers Rental via le champ "rental"
  List<Message> findByRental_Id(Long rentalId);
}


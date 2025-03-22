package com.rental.backend.service;

import com.rental.backend.dto.RentalDTO;
import com.rental.backend.entity.Rental;
import com.rental.backend.entity.User;
import com.rental.backend.exception.DuplicateResourceException;
import com.rental.backend.exception.ResourceNotFoundException;
import com.rental.backend.repository.RentalRepository;
import com.rental.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

  private final RentalRepository rentalRepository;
  private final UserRepository userRepository;

  @Autowired
  public RentalService(RentalRepository rentalRepository, UserRepository userRepository){
    this.rentalRepository = rentalRepository;
    this.userRepository = userRepository;
  }

  public List<RentalDTO> getAllRentals(){
    List<Rental> rentals = rentalRepository.findAll();
    return rentals.stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
  }

  public RentalDTO getRentalById(Long id){
    Rental rental = rentalRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Location non trouvée"));
    return convertToDTO(rental);
  }

  public RentalDTO createRental(RentalDTO rentalDTO) {
    Rental rental = new Rental();
    rental.setName(rentalDTO.getName());
    rental.setDescription(rentalDTO.getDescription());
    rental.setPrice(rentalDTO.getPrice());
    rental.setSurface(rentalDTO.getSurface());
    rental.setPicture(rentalDTO.getPicture()); // URL de l'image générée par le contrôleur

    // Récupérer l'utilisateur connecté
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    User owner = userRepository.findByEmail(email)
      .orElseThrow(() -> new ResourceNotFoundException("Propriétaire non trouvé"));
    rental.setOwner(owner);

    rental.setCreatedAt(LocalDateTime.now());
    rental.setUpdatedAt(LocalDateTime.now());

    rental = rentalRepository.save(rental);
    return convertToDTO(rental);
  }

  public RentalDTO update(Long id, RentalDTO rentalDTO) {
    Rental existingRental = rentalRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + id));

    existingRental.setName(rentalDTO.getName());
    existingRental.setDescription(rentalDTO.getDescription());
    existingRental.setPrice(rentalDTO.getPrice());
    existingRental.setSurface(rentalDTO.getSurface());

    existingRental.setUpdatedAt(LocalDateTime.now());

    Rental updatedRental = rentalRepository.save(existingRental);
    return convertToDTO(updatedRental);
  }

  private RentalDTO convertToDTO(Rental rental) {
    RentalDTO dto = new RentalDTO();
    dto.setId(rental.getId());
    dto.setName(rental.getName());
    dto.setDescription(rental.getDescription());
    dto.setPrice(rental.getPrice());
    dto.setSurface(rental.getSurface());
    dto.setPicture(rental.getPicture());
    dto.setOwnerId(rental.getOwner().getId());
    dto.setCreatedAt(rental.getCreatedAt());
    dto.setUpdatedAt(rental.getUpdatedAt());
    return dto;
  }

}


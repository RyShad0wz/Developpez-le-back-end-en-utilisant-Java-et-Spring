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

  public RentalDTO createRental(RentalDTO rentalDTO){
    Rental rental = new Rental();
    rental.setName(rentalDTO.getName());
    rental.setDescription(rentalDTO.getDescription());
    rental.setPrice(rentalDTO.getPrice());
    rental.setSurface(rentalDTO.getSurface());
    rental.setPicture(rentalDTO.getPicture());

    if (rentalRepository.existsByPicture(rental.getPicture())) {
      throw new DuplicateResourceException("L'URL de l'image existe déjà.");
    }

    rental.setCreatedAt(rentalDTO.getCreatedAt());
    rental.setCreatedAt(rentalDTO.getUpdatedAt());

    // Récupérer l'utilisateur connecté depuis le SecurityContext
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();  // suppose que le username est l'email
    User owner = userRepository.findByEmail(email)
      .orElseThrow(() -> new ResourceNotFoundException("Propriétaire non trouvé"));
    rental.setOwner(owner);

    // Définir createdAt et updatedAt si nécessaire
    rental = rentalRepository.save(rental);
    return convertToDTO(rental);
  }

  private RentalDTO convertToDTO(Rental rental){
    RentalDTO dto = new RentalDTO();
    dto.setId(rental.getId());
    dto.setName(rental.getName());
    dto.setDescription(rental.getDescription());
    dto.setPrice(rental.getPrice());
    dto.setSurface(rental.getSurface());
    dto.setPicture(rental.getPicture());
    dto.setOwnerId(rental.getOwner().getId());
    dto.setCreatedAt(LocalDateTime.now());
    dto.setUpdatedAt(LocalDateTime.now());
    return dto;
  }

  public RentalDTO update(Long id, RentalDTO rentalDTO) {

    Rental existingRental = rentalRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + id));

    if (rentalDTO.getPicture() != null) {
      // Vérifie si l'URL de l'image a changé
      if (!existingRental.getPicture().equals(rentalDTO.getPicture())) {
        // Vérifie si la nouvelle URL existe déjà
        if (rentalRepository.existsByPicture(rentalDTO.getPicture())) {
          throw new DuplicateResourceException("L'URL de l'image existe déjà.");
        }
      }
      existingRental.setPicture(rentalDTO.getPicture());
    }

    existingRental.setName(rentalDTO.getName());
    existingRental.setDescription(rentalDTO.getDescription());
    existingRental.setPrice(rentalDTO.getPrice());
    existingRental.setSurface(rentalDTO.getSurface());
    existingRental.setPicture(rentalDTO.getPicture());
    existingRental.setUpdatedAt(LocalDateTime.now());
    existingRental.setCreatedAt(LocalDateTime.now());

    // Sauvegarde le rental mis à jour
    Rental updatedRental = rentalRepository.save(existingRental);

    // Convertit et retourne le DTO correspondant
    return convertToDTO(updatedRental);
  }


}


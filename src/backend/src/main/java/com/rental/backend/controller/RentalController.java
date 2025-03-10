package com.rental.backend.controller;

import com.rental.backend.dto.RentalDTO;
import com.rental.backend.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

  private final RentalService rentalService;

  @Autowired
  public RentalController(RentalService rentalService){
    this.rentalService = rentalService;
  }

  @GetMapping
  public ResponseEntity<List<RentalDTO>> getAllRentals(){
    List<RentalDTO> rentals = rentalService.getAllRentals();
    return ResponseEntity.ok(rentals);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RentalDTO> getRentalById(@PathVariable Long id){
    RentalDTO rental = rentalService.getRentalById(id);
    return ResponseEntity.ok(rental);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RentalDTO> createRental(@RequestBody RentalDTO rentalDTO){
    RentalDTO createdRental = rentalService.createRental(rentalDTO);
    return new ResponseEntity<>(createdRental, HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RentalDTO> updateRental(@PathVariable Long id, @RequestBody RentalDTO rentalDTO) {
    RentalDTO updatedRental = rentalService.update(id, rentalDTO);
    return ResponseEntity.ok(updatedRental);
  }


  // Vous pouvez ajouter ici des endpoints pour mettre à jour ou supprimer une location.
}


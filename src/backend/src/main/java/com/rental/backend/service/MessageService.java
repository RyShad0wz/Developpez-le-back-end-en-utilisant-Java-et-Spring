package com.rental.backend.service;

import com.rental.backend.dto.MessageDTO;
import com.rental.backend.dto.MessageResponseDTO;
import com.rental.backend.entity.Message;
import com.rental.backend.entity.Rental;
import com.rental.backend.entity.User;
import com.rental.backend.repository.MessageRepository;
import com.rental.backend.repository.RentalRepository;
import com.rental.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private UserRepository userRepository;

  public MessageResponseDTO sendMessage(MessageDTO messageDTO) {
    // Récupérer la location et l'utilisateur
    Rental rental = rentalRepository.findById(messageDTO.getRentalId())
      .orElseThrow(() -> new IllegalArgumentException("Location non trouvée"));

    User user = userRepository.findById(messageDTO.getUserId())
      .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

    // Créer et sauvegarder le message
    Message message = new Message();
    message.setRental(rental);
    message.setUser(user);
    message.setMessage(messageDTO.getMessage());
    messageRepository.save(message);

    // Retourner la réponse de succès
    return new MessageResponseDTO("Message send with success");
  }
}

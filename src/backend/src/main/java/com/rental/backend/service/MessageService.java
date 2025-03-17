package com.rental.backend.service;

import com.rental.backend.dto.MessageDTO;
import com.rental.backend.entity.Message;
import com.rental.backend.entity.Rental;
import com.rental.backend.entity.User;
import com.rental.backend.repository.MessageRepository;
import com.rental.backend.repository.RentalRepository;
import com.rental.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

  private final MessageRepository messageRepository;
  private final RentalRepository rentalRepository;
  private final UserRepository userRepository;

  @Autowired
  public MessageService(MessageRepository messageRepository, RentalRepository rentalRepository, UserRepository userRepository){
    this.messageRepository = messageRepository;
    this.rentalRepository = rentalRepository;
    this.userRepository = userRepository;
  }

  public List<MessageDTO> getMessagesByRentalId(Long rental_id){
    List<Message> messages = messageRepository.findByRental_Id(rental_id);
    return messages.stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
  }

  public MessageDTO sendMessage(MessageDTO messageDTO){
    Message message = new Message();
    message.setMessage(messageDTO.getMessage());
    // Récupérer la location
    Rental rental = rentalRepository.findById(messageDTO.getRentalId())
      .orElseThrow(() -> new RuntimeException("Location non trouvée"));
    message.setRental(rental);
    // Récupérer l'utilisateur
    User user = userRepository.findById(messageDTO.getUserId())
      .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    message.setUser(user);
    message = messageRepository.save(message);
    return convertToDTO(message);
  }

  private MessageDTO convertToDTO(Message message){
    MessageDTO dto = new MessageDTO();
    dto.setId(message.getId());
    dto.setRentalId(message.getRental().getId());
    dto.setUserId(message.getUser().getId());
    dto.setMessage(message.getMessage());
    return dto;
  }
}

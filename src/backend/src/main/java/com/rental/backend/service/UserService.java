package com.rental.backend.service;

import com.rental.backend.dto.UserDTO;
import com.rental.backend.entity.User;
import com.rental.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public UserDTO getUserById(Long id){
    User user = userRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    return convertToDTO(user);
  }

  public UserDTO createUser(UserDTO userDTO, String rawPassword) {
    // Pour cet exemple, on stocke le mot de passe tel quel (à remplacer par un hachage)
    User user = new User();
    user.setEmail(userDTO.getEmail());
    user.setName(userDTO.getName());
    user.setPassword(rawPassword);
    // Vous pouvez également définir createdAt/updatedAt ici
    user = userRepository.save(user);
    return convertToDTO(user);
  }

  private UserDTO convertToDTO(User user){
    // On suppose que UserDTO possède un constructeur (id, name, email, createdAt, updatedAt)
    return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
  }
}


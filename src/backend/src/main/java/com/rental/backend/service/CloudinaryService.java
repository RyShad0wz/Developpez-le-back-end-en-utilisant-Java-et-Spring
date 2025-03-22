package com.rental.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

  @Autowired
  private Cloudinary cloudinary;

  /**
   * Upload une image vers Cloudinary et retourne l'URL publique.
   *
   * @param file Le fichier image à uploader.
   * @return L'URL publique de l'image uploadée.
   * @throws IOException Si une erreur survient lors de l'upload.
   */
  public String uploadImage(MultipartFile file) throws IOException {
    // Uploader l'image vers Cloudinary
    Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

    // Récupérer l'URL de l'image uploadée
    return uploadResult.get("url").toString();
  }
}

package org.movie.backend.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Log4j2
@RequestMapping("/api")
public class UploadController {

  @PostMapping("/uploadAxios")
  public void uploadFile(MultipartFile[] file) {
    for (MultipartFile uploadFile : file){
      String originalName = uploadFile.getOriginalFilename();
      assert originalName != null;
      String fileName = originalName.substring(originalName.lastIndexOf("\\")+1);
      log.info("Uploading file: {}", fileName);
    }
  }

}

package com.email.mailCreate.controller;

import com.email.mailCreate.pojo.EmailRequest;
import com.email.mailCreate.service.EmailGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "https://mail.google.com")
@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailGeneratorController {
    EmailGeneratorService emailGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<?> getEmail(@RequestBody EmailRequest emailRequest) {
        String response = emailGeneratorService.generateEmailReply(emailRequest);
        return ResponseEntity.ok(response);
    }
}

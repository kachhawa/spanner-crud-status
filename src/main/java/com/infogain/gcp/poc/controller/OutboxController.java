package com.infogain.gcp.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.gcp.poc.model.OutboxModel;
import com.infogain.gcp.poc.service.OutboxService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
public class OutboxController {

    @Autowired
    private OutboxService outboxService;

    @GetMapping(value="/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {
        return "Ready to serve requests";
    }

    @PostMapping(value = "/api/outbox/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveOutboxModel(Exception ex, @RequestBody OutboxModel outboxModel){
        log.info("Received Outbox Model {}",outboxModel.toString());
        try {
        	return new ResponseEntity<>(outboxService.saveOutboxModel(outboxModel), HttpStatus.OK);
        } catch(DataIntegrityViolationException d) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
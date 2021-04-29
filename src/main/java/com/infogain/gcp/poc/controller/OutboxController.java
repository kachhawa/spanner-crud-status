package com.infogain.gcp.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public OutboxModel saveOutboxModel(@RequestBody OutboxModel outboxModel){
        log.info("Received Outbox Model {}",outboxModel.toString());
        return outboxService.saveOutboxModel(outboxModel);
    }
}
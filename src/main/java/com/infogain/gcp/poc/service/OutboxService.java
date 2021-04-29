package com.infogain.gcp.poc.service;

import com.infogain.gcp.poc.entity.OutboxEntity;
import com.infogain.gcp.poc.model.OutboxModel;
import com.infogain.gcp.poc.repository.OutboxRepository;
import com.infogain.gcp.poc.repository.OutboxStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class OutboxService {

    @Autowired
    private OutboxStatusRepository outboxStatusRepository;

    @Autowired
    private OutboxRepository outboxRepository;

    @SuppressWarnings("all")
    @Transactional
    public OutboxModel saveOutboxModel(OutboxModel outboxModel){
        OutboxEntity outboxEntity = outboxModel.buildEntity();
        log.info("Saving OutboxEntity={}",outboxEntity.toString());
        outboxRepository.getSpannerTemplate().insert(outboxEntity);
        log.info("Saved OutboxEntity={}",outboxEntity.toString());
        return outboxEntity.buildModel();
    }

}
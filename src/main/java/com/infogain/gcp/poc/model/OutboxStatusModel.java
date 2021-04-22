package com.infogain.gcp.poc.model;

import com.google.cloud.Timestamp;
import com.infogain.gcp.poc.entity.OutboxStatusEntity;
import lombok.*;
import org.apache.commons.beanutils.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"locator","version","destination"})
public class OutboxStatusModel {

    private String locator;
    private String version;
    private String destination;
    private String status;
    private Timestamp created;
    private Timestamp updated;
    private String instance;

    @SneakyThrows
    public OutboxStatusEntity buildEntity() {
        OutboxStatusEntity outboxStatusEntity = new OutboxStatusEntity();
        BeanUtils.copyProperties(outboxStatusEntity, this);
        return outboxStatusEntity;
    }

}
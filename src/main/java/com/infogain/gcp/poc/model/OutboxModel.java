package com.infogain.gcp.poc.model;

import com.google.cloud.Timestamp;
import com.infogain.gcp.poc.entity.OutboxEntity;
import lombok.*;
import org.apache.commons.beanutils.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OutboxModel {

    private String locator;
    private Integer version;
    private String parent_locator;
    private String created;
    private String data;

    @SneakyThrows
    public OutboxEntity buildEntity(){
        OutboxEntity outboxEntity = new OutboxEntity();
        outboxEntity.setData(data);
        outboxEntity.setLocator(locator);
        outboxEntity.setParent_locator(parent_locator);
        outboxEntity.setVersion(version);
        outboxEntity.setCreated(Timestamp.parseTimestamp(created));
        return outboxEntity;
    }

}
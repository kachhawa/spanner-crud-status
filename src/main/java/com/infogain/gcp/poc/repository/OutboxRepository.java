package com.infogain.gcp.poc.repository;

import com.infogain.gcp.poc.entity.OutboxEntity;
import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepository extends SpannerRepository<OutboxEntity, String> {
}
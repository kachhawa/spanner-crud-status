package com.infogain.gcp.poc.repository;

import com.infogain.gcp.poc.entity.OutboxStatusEntity;
import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxStatusRepository extends SpannerRepository<OutboxStatusEntity, String> {
}
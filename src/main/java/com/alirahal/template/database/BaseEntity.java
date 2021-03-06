package com.alirahal.template.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid1")
    private UUID id;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private OffsetDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "date_updated")
    private OffsetDateTime updateDateTime;

}
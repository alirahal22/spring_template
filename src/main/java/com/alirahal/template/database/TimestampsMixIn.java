package com.alirahal.template.database;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.OffsetDateTime;

public abstract class TimestampsMixIn {

    @JsonIgnore
    OffsetDateTime createDateTime;
    @JsonIgnore
    OffsetDateTime updateDateTime;
}

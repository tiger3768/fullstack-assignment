package com.ucubeinterview.timer.model;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "timers")
public class Timer {

    @Id
    private String id;

    @NotBlank(message = "Counter name cannot be empty")
    @Size(min = 2, max = 32, message = "Counter name must be 2–32 characters")
    @Field("name")
    private String name;

    @NotNull(message = "Target date/time cannot be null")
    @Future(message = "Target date/time must be in the future")
    @Field("targetDate")
    private Instant targetDate;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}

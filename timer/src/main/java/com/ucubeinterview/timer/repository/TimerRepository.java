package com.ucubeinterview.timer.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.ucubeinterview.timer.model.Timer;

public interface TimerRepository extends MongoRepository<Timer, String> {
}

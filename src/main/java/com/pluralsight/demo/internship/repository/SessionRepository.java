package com.pluralsight.demo.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository<SessionEntity> extends JpaRepository<SessionEntity, Long> {

    List<SessionEntity> findByTrackIgnoreCase (String track);

}

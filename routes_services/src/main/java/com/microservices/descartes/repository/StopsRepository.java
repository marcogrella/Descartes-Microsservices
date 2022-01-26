package com.microservices.descartes.repository;

import com.microservices.descartes.model.Stops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopsRepository extends JpaRepository<Stops, Long> {
}

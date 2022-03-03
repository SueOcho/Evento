package com.eventus.eventus.repository;

import com.eventus.eventus.model.CompraEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraEntradaRepository extends JpaRepository<CompraEntrada, Integer> {
}

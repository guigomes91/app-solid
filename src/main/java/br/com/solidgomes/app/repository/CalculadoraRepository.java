package br.com.solidgomes.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.solidgomes.app.model.CalculadoraEntity;

@Repository
public interface CalculadoraRepository extends JpaRepository<CalculadoraEntity, UUID> {}

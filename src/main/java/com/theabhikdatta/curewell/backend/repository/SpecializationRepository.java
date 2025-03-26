package com.theabhikdatta.curewell.backend.repository;

import com.theabhikdatta.curewell.backend.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecializationRepository extends JpaRepository<Specialization, String> {

    Optional<Specialization> findBySpecializationCode(String specializationCode);
}

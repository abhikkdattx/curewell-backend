package com.theabhikdatta.curewell.backend.repository;

import com.theabhikdatta.curewell.backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

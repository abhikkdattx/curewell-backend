package com.theabhikdatta.curewell.backend.repository;

import com.theabhikdatta.curewell.backend.entity.Specialization;
import com.theabhikdatta.curewell.backend.entity.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {

    List<Surgery> findBySurgeryDateAndSurgeryCategory_SpecializationCode(LocalDate surgeryDate, String specializationCode);

}

package com.theabhikdatta.curewell.backend.repository;

import com.theabhikdatta.curewell.backend.entity.DoctorSpecialization;
import com.theabhikdatta.curewell.backend.entity.DoctorSpecializationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, DoctorSpecializationId> {

    DoctorSpecialization findByDoctor_DoctorIdAndSpecialization_SpecializationCode(Long doctorId, String specializationCode);

    List<DoctorSpecialization> findByDoctor_DoctorId(Long doctorId);

    List<DoctorSpecialization> findBySpecialization_SpecializationCode(String specializationCode);
}

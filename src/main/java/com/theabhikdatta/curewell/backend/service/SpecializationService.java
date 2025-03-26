package com.theabhikdatta.curewell.backend.service;

import com.theabhikdatta.curewell.backend.entity.Doctor;
import com.theabhikdatta.curewell.backend.entity.DoctorSpecializationId;
import com.theabhikdatta.curewell.backend.entity.Specialization;

import java.util.List;

public interface SpecializationService {

    List<Specialization> getAllSpecializations();

    Specialization getSpecializationBySpecializationCode(String specializationCode);

    Specialization addSpecialization(Specialization specialization);

    Specialization updateSpecialization(String specializationCode, Specialization specialization);

    void deleteSpecialization(String specializationCode);

}

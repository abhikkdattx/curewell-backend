package com.theabhikdatta.curewell.backend.service;

import com.theabhikdatta.curewell.backend.entity.DoctorSpecialization;
import com.theabhikdatta.curewell.backend.entity.DoctorSpecializationId;
import com.theabhikdatta.curewell.backend.payload.DoctorSpecializationDto;

import java.util.List;

public interface DoctorSpecializationService {

    List<DoctorSpecialization> getAllDoctorSpecializations();

    DoctorSpecializationDto addDoctorSpecialization(DoctorSpecializationDto doctorSpecializationDto);

    DoctorSpecialization addDoctorSpecialization(DoctorSpecializationId id, DoctorSpecialization doctorSpecialization);

    DoctorSpecialization updateDoctorSpecialization(Long doctorId, String specializationCode, DoctorSpecialization doctorSpecialization);

    void deleteDoctorSpecialization(Long doctorId, String specializationCode);

    List<DoctorSpecialization> getDoctorSpecializationByDoctorId(Long doctorId);

    List<DoctorSpecialization> getDoctorSpecializationBySpecializationCode(String specializationCode);

}

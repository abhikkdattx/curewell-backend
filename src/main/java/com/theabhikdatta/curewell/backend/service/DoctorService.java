package com.theabhikdatta.curewell.backend.service;

import com.theabhikdatta.curewell.backend.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();

    Doctor getDoctorById(Long doctorId);

    Doctor addDoctor(Doctor doctor);

    Doctor updateDoctor(Long doctorId, Doctor doctor);

    void deleteDoctor(Long doctorId);

}

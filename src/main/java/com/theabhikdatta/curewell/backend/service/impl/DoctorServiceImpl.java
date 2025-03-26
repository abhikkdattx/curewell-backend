package com.theabhikdatta.curewell.backend.service.impl;

import com.theabhikdatta.curewell.backend.entity.Doctor;
import com.theabhikdatta.curewell.backend.exception.ResourceNotFoundException;
import com.theabhikdatta.curewell.backend.repository.DoctorRepository;
import com.theabhikdatta.curewell.backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long doctorId) {
        Doctor doctor = doctorRepository
                .findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("doctor", "doctorId", doctorId));
        return doctor;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Long doctorId,
                               Doctor doctor) {
        Doctor updateDoctor = doctorRepository
                .findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("doctor", "doctorId", doctorId));
        updateDoctor.setDoctorName(doctor.getDoctorName());
        return doctorRepository.save(updateDoctor);
    }

    @Override
    public void deleteDoctor(Long doctorId) {
        Doctor doctor = doctorRepository
                .findById(doctorId)
                .orElseThrow(() ->new ResourceNotFoundException("doctor", "doctorId", doctorId));
        doctorRepository.delete(doctor);
    }
}

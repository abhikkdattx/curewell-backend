package com.theabhikdatta.curewell.backend.service.impl;

import com.theabhikdatta.curewell.backend.entity.Doctor;
import com.theabhikdatta.curewell.backend.entity.DoctorSpecialization;
import com.theabhikdatta.curewell.backend.entity.DoctorSpecializationId;
import com.theabhikdatta.curewell.backend.entity.Specialization;
import com.theabhikdatta.curewell.backend.exception.ResourceNotFoundException;
import com.theabhikdatta.curewell.backend.payload.DoctorSpecializationDto;
import com.theabhikdatta.curewell.backend.repository.DoctorRepository;
import com.theabhikdatta.curewell.backend.repository.DoctorSpecializationRepository;
import com.theabhikdatta.curewell.backend.repository.SpecializationRepository;
import com.theabhikdatta.curewell.backend.service.DoctorSpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoctorSpecializationServiceImpl implements DoctorSpecializationService {

    @Autowired
    private DoctorSpecializationRepository doctorSpecializationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public List<DoctorSpecialization> getAllDoctorSpecializations() {
        return doctorSpecializationRepository.findAll();
    }

    @Override
    public DoctorSpecializationDto addDoctorSpecialization(DoctorSpecializationDto doctorSpecializationDto) {
        // getting doctor id first
        Doctor doctor = doctorRepository
                .findById(doctorSpecializationDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "doctorId", doctorSpecializationDto.getDoctorId()));
        // getting specializationCode next
        Specialization specialization = specializationRepository
                .findBySpecializationCode(doctorSpecializationDto.getSpecializationCode())
                .orElseThrow(() -> new ResourceNotFoundException("Specialization", "specializationCode", doctorSpecializationDto.getSpecializationCode()));

        //creating and setting the composite keys
        DoctorSpecializationId doctorSpecializationId = new DoctorSpecializationId();
        doctorSpecializationId.setDoctorId(doctorSpecializationDto.getDoctorId());
        doctorSpecializationId.setSpecializationCode(doctorSpecializationDto.getSpecializationCode());

        //setting the values
        DoctorSpecialization doctorSpecialization = new DoctorSpecialization();
        doctorSpecialization.setId(doctorSpecializationId);
        doctorSpecialization.setDoctor(doctor);
        doctorSpecialization.setSpecialization(specialization);
        doctorSpecialization.setSpecializationDate(LocalDate.now());

        //saving it
        doctorSpecializationRepository.save(doctorSpecialization);

        //and finally return the dto with updated specialization date
        return new DoctorSpecializationDto(
                doctorSpecializationDto.getDoctorId(),
                doctorSpecializationDto.getSpecializationCode(),
                doctorSpecialization.getSpecializationDate()
        );
    }

    @Override
    public DoctorSpecialization addDoctorSpecialization(DoctorSpecializationId id, DoctorSpecialization doctorSpecialization) {
        //Retrieve doctor and specialization using the provided composite key
        Doctor doctor = doctorRepository
                .findById(id.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "doctorId", id.getDoctorId()));
        Specialization specialization = specializationRepository
                .findBySpecializationCode(id.getSpecializationCode())
                .orElseThrow(() -> new ResourceNotFoundException("Specialization", "specializationCode", id.getSpecializationCode()));
        // setting the retrieved entities in doctor specialization
        doctorSpecialization.setDoctor(doctor);
        doctorSpecialization.setSpecialization(specialization);
        doctorSpecialization.setId(id);

        //saving and returning the docSPec entity
        return doctorSpecializationRepository.save(doctorSpecialization);
    }

    @Override
    public DoctorSpecialization updateDoctorSpecialization(Long doctorId,
                                                           String specializationCode,
                                                           DoctorSpecialization doctorSpecialization) {
        //first I created the composite key because in entities we have composite key
        DoctorSpecializationId id = new DoctorSpecializationId(doctorId, specializationCode);
        DoctorSpecialization updateDoctorSpecialization = doctorSpecializationRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("doctor_specialization", "doctorSpecializationId", id));
        updateDoctorSpecialization.setSpecializationDate(doctorSpecialization.getSpecializationDate());
        return doctorSpecializationRepository.save(updateDoctorSpecialization);
    }

    @Override
    public void deleteDoctorSpecialization(Long doctorId,
                                           String specializationCode) {
        //first I created the composite key because in entities we have composite key
        DoctorSpecializationId id = new DoctorSpecializationId(doctorId, specializationCode);
        DoctorSpecialization deleteDoctorSpecialization = doctorSpecializationRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("doctor_specialization", "doctorSpecializationId", id));
        doctorSpecializationRepository.deleteById(id);
    }

    @Override
    public List<DoctorSpecialization> getDoctorSpecializationByDoctorId(Long doctorId) {
        return doctorSpecializationRepository.findByDoctor_DoctorId(doctorId);
    }

    @Override
    public List<DoctorSpecialization> getDoctorSpecializationBySpecializationCode(String specializationCode) {
        return doctorSpecializationRepository.findBySpecialization_SpecializationCode(specializationCode);
    }
}

package com.theabhikdatta.curewell.backend.service.impl;

import com.theabhikdatta.curewell.backend.entity.Doctor;
import com.theabhikdatta.curewell.backend.entity.Specialization;
import com.theabhikdatta.curewell.backend.entity.Surgery;
import com.theabhikdatta.curewell.backend.exception.ResourceNotFoundException;
import com.theabhikdatta.curewell.backend.payload.SurgeryDto;
import com.theabhikdatta.curewell.backend.repository.DoctorRepository;
import com.theabhikdatta.curewell.backend.repository.SpecializationRepository;
import com.theabhikdatta.curewell.backend.repository.SurgeryRepository;
import com.theabhikdatta.curewell.backend.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SurgeryServiceImpl implements SurgeryService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SurgeryRepository surgeryRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public List<Surgery> getAllSurgeries() {
        return surgeryRepository.findAll();
    }

    @Override
    public List<Surgery> getAllSurgeryTypeForToday(String specializationCode) {
        return surgeryRepository.findBySurgeryDateAndSurgeryCategory_SpecializationCode(LocalDate.now(), specializationCode);
    }

    @Override
    public Surgery getSurgeryById(Long surgeryId) {
        Surgery surgery = surgeryRepository
                .findById(surgeryId)
                .orElseThrow(() -> new ResourceNotFoundException("surgery", "surgeryId", surgeryId));
        return surgery;
    }

    @Override
    public SurgeryDto addSurgery(SurgeryDto surgeryDto) {
        //fetching doctor by doctorId
        Doctor doctor = doctorRepository
                .findById(surgeryDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "doctorId", surgeryDto.getDoctorId()));
        //fetching specialization by specializationCode
        Specialization specialization = specializationRepository
                .findBySpecializationCode(surgeryDto.getSpecializationCode())
                .orElseThrow(() -> new ResourceNotFoundException("Specialization", "specializationCode", surgeryDto.getSpecializationCode()));
        //creating the surgery entity
        Surgery surgery = new Surgery();
        surgery.setDoctor(doctor);
        surgery.setSurgeryCategory(specialization);
        surgery.setSurgeryDate(surgeryDto.getSurgeryDate());
        surgery.setStartTime(surgeryDto.getStartTime());
        surgery.setEndTime(surgeryDto.getEndTime());

        //saving the surgery entity
        surgeryRepository.save(surgery);

        return surgeryDto;
    }

    @Override
    public Surgery addSurgery(Long doctorId, String surgeryCategory, Surgery surgery) {
        // fetching doctor entity by doctorId first
        Doctor doctor = doctorRepository
                .findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "doctorId", doctorId));

        // fetching specialization by specialization code next
        Specialization specialization = specializationRepository
                .findBySpecializationCode(surgeryCategory)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization", "specializationCode", surgeryCategory));

        // setting doctor and surgery entity
        surgery.setDoctor(doctor);
        surgery.setSurgeryCategory(specialization);

        // save and return the surgery entity
        return surgeryRepository.save(surgery);
    }

    @Override
    public Surgery updateSurgery(Long surgeryId, Surgery surgery) {
        Surgery updateSurgery = surgeryRepository
                .findById(surgeryId)
                .orElseThrow(() -> new ResourceNotFoundException("surgery", "surgeryId", surgeryId));
        updateSurgery.setSurgeryDate(surgery.getSurgeryDate());
        updateSurgery.setStartTime(surgery.getStartTime());
        updateSurgery.setEndTime(surgery.getEndTime());
        return surgeryRepository.save(updateSurgery);
    }

    @Override
    public void DeleteSurgery(Long surgeryId) {
        Surgery surgery = surgeryRepository
                .findById(surgeryId)
                .orElseThrow(() -> new ResourceNotFoundException("surgery", "surgeryId", surgeryId));
        surgeryRepository.delete(surgery);
    }
}

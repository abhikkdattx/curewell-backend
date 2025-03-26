package com.theabhikdatta.curewell.backend.service.impl;

import com.theabhikdatta.curewell.backend.entity.Specialization;
import com.theabhikdatta.curewell.backend.exception.ResourceNotFoundException;
import com.theabhikdatta.curewell.backend.repository.SpecializationRepository;
import com.theabhikdatta.curewell.backend.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization getSpecializationBySpecializationCode(String specializationCode) {
        Specialization specialization = specializationRepository
                .findById(specializationCode)
                .orElseThrow(() -> new ResourceNotFoundException("specialization", "specializationCode", specializationCode));
        return specialization;
    }

    @Override
    public Specialization addSpecialization(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    @Override
    public Specialization updateSpecialization(String specializationCode,
                                               Specialization specialization) {
        Specialization updateSpecialization = specializationRepository
                .findById(specializationCode)
                .orElseThrow(() -> new ResourceNotFoundException("specialization", "specializationCode", specializationCode));
        updateSpecialization.setSpecializationName(specialization.getSpecializationName());
        return specializationRepository.save(updateSpecialization);
    }

    @Override
    public void deleteSpecialization(String specializationCode) {
        Specialization specialization = specializationRepository
                .findById(specializationCode)
                .orElseThrow(() -> new ResourceNotFoundException("specialization", "specializationCode", specializationCode));
        specializationRepository.delete(specialization);
    }
}

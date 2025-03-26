package com.theabhikdatta.curewell.backend.controller;

import com.theabhikdatta.curewell.backend.entity.Doctor;
import com.theabhikdatta.curewell.backend.entity.DoctorSpecialization;
import com.theabhikdatta.curewell.backend.entity.DoctorSpecializationId;
import com.theabhikdatta.curewell.backend.entity.Specialization;
import com.theabhikdatta.curewell.backend.exception.ResourceNotFoundException;
import com.theabhikdatta.curewell.backend.payload.DoctorSpecializationDto;
import com.theabhikdatta.curewell.backend.repository.DoctorRepository;
import com.theabhikdatta.curewell.backend.repository.SpecializationRepository;
import com.theabhikdatta.curewell.backend.service.DoctorSpecializationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/doctorSpecialization")
@RestController
public class DoctorSpecializationController {

    @Autowired
    private DoctorSpecializationService doctorSpecializationService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @GetMapping
    public ResponseEntity<List<DoctorSpecialization>> getAllDoctorSpecialization(){
        var data = doctorSpecializationService.getAllDoctorSpecializations();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<DoctorSpecialization>> getDoctorSpecializationByDoctorId(@PathVariable("doctorId") Long doctorId){
        var data = doctorSpecializationService.getDoctorSpecializationByDoctorId(doctorId);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/specialization/{specializationCode}")
    public ResponseEntity<List<DoctorSpecialization>> getDoctorSpecializationBySpecializationCode(@PathVariable("specializationCode") String specializationCode){
        var data = doctorSpecializationService.getDoctorSpecializationBySpecializationCode(specializationCode);
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<DoctorSpecializationDto> addDoctorSpecialization(@Valid @RequestBody DoctorSpecializationDto doctorSpecializationDto){
        try{
            var data = doctorSpecializationService.addDoctorSpecialization(doctorSpecializationDto);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (RuntimeException ex){
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/{doctorId}/{specializationCode}")
    public ResponseEntity<DoctorSpecialization> addDoctorSpecialization(
            @PathVariable("doctorId") Long doctorId,
            @PathVariable("specializationCode") String specializationCode,
            @Valid @RequestBody DoctorSpecialization doctorSpecialization){
        // creating the composite key
        DoctorSpecializationId id = new DoctorSpecializationId(doctorId, specializationCode);

        var data = doctorSpecializationService.addDoctorSpecialization(id, doctorSpecialization);

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{doctorId}/{specializationCode}")
    public ResponseEntity<DoctorSpecialization> updateDoctorSpecialization(@PathVariable("doctorId") Long doctorId,
                                                                           @PathVariable("specializationCode") String specializationCode,
                                                                           @Valid @RequestBody DoctorSpecialization doctorSpecialization ){
        var data = doctorSpecializationService.updateDoctorSpecialization(doctorId, specializationCode, doctorSpecialization);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{doctorId}/{specializationCode}")
    public ResponseEntity<Void> deleteDoctorSpecialization(@PathVariable("doctorId") Long doctorId,
                                                           @PathVariable("specializationCode") String specializationCode){
        doctorSpecializationService.deleteDoctorSpecialization(doctorId, specializationCode);
        return ResponseEntity.noContent().build();
    }
}

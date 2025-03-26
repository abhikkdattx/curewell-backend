package com.theabhikdatta.curewell.backend.controller;

import com.theabhikdatta.curewell.backend.entity.Specialization;
import com.theabhikdatta.curewell.backend.entity.Surgery;
import com.theabhikdatta.curewell.backend.exception.ResourceNotFoundException;
import com.theabhikdatta.curewell.backend.payload.SurgeryDto;
import com.theabhikdatta.curewell.backend.repository.DoctorRepository;
import com.theabhikdatta.curewell.backend.repository.SpecializationRepository;
import com.theabhikdatta.curewell.backend.service.SurgeryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/surgery")
@RestController
public class SurgeryController {

    @Autowired
    private SurgeryService surgeryService;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public ResponseEntity<List<Surgery>> getAllSurgeries(){
        var data = surgeryService.getAllSurgeries();
        return ResponseEntity.ok(data);
    }

    @GetMapping("specialization/{specializationCode}")
    public ResponseEntity<List<Surgery>> getAllSurgeryTypeForToday(@PathVariable("specializationCode") String specializationCode){
        var data = surgeryService.getAllSurgeryTypeForToday(specializationCode);
        return ResponseEntity.ok(data);
    }


    @GetMapping("/{surgeryId}")
    public ResponseEntity<Surgery> getSurgeryById(@PathVariable("surgeryId") Long surgeryId){
        var data = surgeryService.getSurgeryById(surgeryId);
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<SurgeryDto> addSurgery(@Valid @RequestBody SurgeryDto surgeryDto){
        var data = surgeryService.addSurgery(surgeryDto);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PostMapping("/{doctorId}/{surgeryCategory}")
    public ResponseEntity<Surgery> addSurgery(@PathVariable("doctorId") Long doctorId,
                                              @PathVariable("surgeryCategory") String surgeryCategory,
                                              @Valid @RequestBody Surgery surgery){
        var data = surgeryService.addSurgery(doctorId, surgeryCategory, surgery);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{surgeryId}")
    public ResponseEntity<Surgery> updateSurgery(@PathVariable("surgeryId") Long surgeryId,
                                                 @Valid @RequestBody Surgery surgery){
        var data = surgeryService.updateSurgery(surgeryId, surgery);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{surgeryId}")
    public ResponseEntity<Void> deleteSurgery(@PathVariable("surgeryId") Long surgeryId){
        surgeryService.DeleteSurgery(surgeryId);
        return ResponseEntity.noContent().build();
    }

}

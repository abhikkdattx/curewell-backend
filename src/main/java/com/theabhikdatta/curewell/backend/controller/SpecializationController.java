package com.theabhikdatta.curewell.backend.controller;

import com.theabhikdatta.curewell.backend.entity.Specialization;
import com.theabhikdatta.curewell.backend.service.SpecializationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/specialization")
@RestController
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @GetMapping
    public ResponseEntity<List<Specialization>> getAllSpecializations(){
        var data = specializationService.getAllSpecializations();
        return ResponseEntity.ok(data);
    }

    @GetMapping("{specializationCode}")
    public ResponseEntity<Specialization> getSpecializationBySpecializationCode(@PathVariable("specializationCode") String specializationCode){
        var data = specializationService.getSpecializationBySpecializationCode(specializationCode);
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<Specialization> addSpecialization(@Valid @RequestBody Specialization specialization){
        var data = specializationService.addSpecialization(specialization);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{specializationCode}")
    public ResponseEntity<Specialization> updateSpecialization(@PathVariable("specializationCode") String specializationCode,
                                                               @Valid @RequestBody Specialization specialization){
        var data = specializationService.updateSpecialization(specializationCode, specialization);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{specializationCode}")
    public ResponseEntity<Void> deleteSpecialization(@PathVariable("specializationCode") String specializationCode){
        specializationService.deleteSpecialization(specializationCode);
        return ResponseEntity.noContent().build();
    }

}

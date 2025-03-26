package com.theabhikdatta.curewell.backend.controller;


import com.theabhikdatta.curewell.backend.entity.Doctor;
import com.theabhikdatta.curewell.backend.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/doctor")
@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        var data = doctorService.getAllDoctors();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorId") Long doctorId){
        var data = doctorService.getDoctorById(doctorId);
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor){
        var data = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorId") Long doctorId,
                                               @Valid @RequestBody Doctor doctor){
        var data = doctorService.updateDoctor(doctorId, doctor);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("doctorId") Long doctorId){
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.noContent().build();
    }
}

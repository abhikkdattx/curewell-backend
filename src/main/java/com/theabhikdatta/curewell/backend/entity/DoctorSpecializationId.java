package com.theabhikdatta.curewell.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DoctorSpecializationId implements Serializable {

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "specialization_code")
    private String specializationCode;
}

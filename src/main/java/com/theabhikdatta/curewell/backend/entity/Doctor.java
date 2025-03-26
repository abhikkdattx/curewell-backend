package com.theabhikdatta.curewell.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "doctor"
)
public class Doctor {
    @Id
    @SequenceGenerator(
            name = "doctor_seq",
            sequenceName = "doctor_seq",
            initialValue = 1001,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctor_seq"
    )
    private Long doctorId;

    @NotEmpty(message = "doctor name is mandatory")
    @Column(name = "doctor_name", nullable = false)
    private String doctorName;


    @OneToMany(
            mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DoctorSpecialization> doctorSpecializations;


    @OneToMany(
            mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Surgery> surgeries;
}

package com.theabhikdatta.curewell.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "specialization"
)
public class Specialization {

    @Id
    @Column(name = "specialization_code", nullable = false)
    private String specializationCode;

    @Column(name = "specialization_name", nullable = false)
    @NotEmpty(message = "specialization name is mandatory")
    private String specializationName;

    @JsonIgnore
    @OneToMany(
            mappedBy = "specialization",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<DoctorSpecialization> doctorSpecializations;

    @JsonIgnore
    @OneToMany(
            mappedBy = "surgeryCategory",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Surgery> surgeries;
}

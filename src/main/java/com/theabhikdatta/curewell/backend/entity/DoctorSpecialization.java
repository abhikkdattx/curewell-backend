package com.theabhikdatta.curewell.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "doctor_specialization"
)
public class DoctorSpecialization {

    @EmbeddedId
    private DoctorSpecializationId id;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @MapsId("doctorId")
    @JoinColumn(
            name = "doctor_id",
            nullable = false
    )

    private Doctor doctor;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @MapsId("specializationCode")
    @JoinColumn(
            name = "specialization_code",
            nullable = false
    )

    private Specialization specialization;

    @Column(name = "specialization_date", nullable = false)
    @NotNull(message = "Specialization date is mandatory")
    private LocalDate specializationDate;
}

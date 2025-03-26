package com.theabhikdatta.curewell.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "surgery")
public class Surgery {

    @Id
    @SequenceGenerator(
            name = "surgery_seq",
            sequenceName = "surgery_seq",
            initialValue = 5001,
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "surgery_seq")
    private Long surgeryId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "doctorSpecializations", "surgeries"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surgery_category", nullable = false)
    private Specialization surgeryCategory;

    @Column(name = "surgery_date", nullable = false)
    @NotNull(message = "Surgery date is mandatory")
    private LocalDate surgeryDate;

    @Column(name = "start_time", nullable = false)
    @NotNull(message = "Start time is mandatory")
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    @NotNull(message = "End time is mandatory")
    private LocalTime endTime;

    @JsonProperty("doctorId")
    public Long getDoctorId() {
        return doctor != null ? doctor.getDoctorId() : null;
    }

    @JsonProperty("specializationCode")
    public String getSpecializationCode() {
        return surgeryCategory != null ? surgeryCategory.getSpecializationCode() : null;
    }
}

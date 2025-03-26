package com.theabhikdatta.curewell.backend.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSpecializationDto {

    private Long doctorId;

    private String specializationCode;

    @NotNull(message = "specialization date is mandatory")
    private LocalDate specializationDate;
}

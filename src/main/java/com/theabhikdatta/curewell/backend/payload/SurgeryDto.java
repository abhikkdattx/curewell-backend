package com.theabhikdatta.curewell.backend.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurgeryDto {

    private Long doctorId;
    private String specializationCode;

    @NotNull(message = "Surgery date is mandatory")
    private LocalDate surgeryDate;

    @NotNull(message = "Start time is mandatory")
    private LocalTime startTime;

    @NotNull(message = "End time is mandatory")
    private LocalTime endTime;
}

package com.theabhikdatta.curewell.backend.service;

import com.theabhikdatta.curewell.backend.entity.Specialization;
import com.theabhikdatta.curewell.backend.entity.Surgery;
import com.theabhikdatta.curewell.backend.payload.SurgeryDto;

import java.time.LocalDate;
import java.util.List;

public interface SurgeryService {

    List<Surgery> getAllSurgeries();

    List<Surgery> getAllSurgeryTypeForToday(String specializationCode);

    Surgery getSurgeryById(Long surgeryId);

    SurgeryDto addSurgery(SurgeryDto surgeryDto);

    Surgery addSurgery(Long doctorId, String surgeryCategory, Surgery surgery);

    Surgery updateSurgery(Long surgeryId, Surgery surgery);

    void DeleteSurgery(Long surgeryId);

}

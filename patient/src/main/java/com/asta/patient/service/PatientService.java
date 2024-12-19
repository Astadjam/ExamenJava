package com.asta.patient.service;

import com.asta.patient.model.Patient;
import com.asta.patient.model.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    public Patient getPatientById(int id) {
        return patientRepository.findById(id).get();
    }
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }
}

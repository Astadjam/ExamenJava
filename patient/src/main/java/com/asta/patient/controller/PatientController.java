package com.asta.patient.controller;

import com.asta.patient.model.Patient;
import com.asta.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/patient")
public class PatientController {
    @Autowired
    public PatientService patientService;

    @PostMapping("/new")
    public Patient addPatient(@RequestBody Patient patient){
        return patientService.createPatient(patient);
    }
    @PostMapping("/update")
    public Patient updatePatient(@RequestBody Patient patient){
        return patientService.updatePatient(patient);
    }
    @DeleteMapping("/delete")
    public void deletePatient(@RequestBody Patient patient){
        patientService.deletePatient(patient.getId());
    }
    @GetMapping("/get")
    public Patient getPatientById(@RequestBody Patient patient){
        return patientService.getPatientById(patient.getId());
    }
    @GetMapping("/all")
    public Iterable<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }
}

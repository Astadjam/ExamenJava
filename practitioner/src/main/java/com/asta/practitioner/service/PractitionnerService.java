package com.asta.practitioner.service;

import com.asta.practitioner.model.Practitionner;
import com.asta.practitioner.model.PractitionnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PractitionnerService {
    @Autowired
    private PractitionnerRepository practitionerRepository;

    public Practitionner createPractitioner(Practitionner practitioner) {
        return practitionerRepository.save(practitioner);
    }
    public Practitionner getPractitionerById(int id) {
        return practitionerRepository.findById(id).get();
    }
    public Practitionner updatePractitioner(Practitionner practitioner) {
        return practitionerRepository.save(practitioner);
    }
    public void deletePractitioner(int id) {
        practitionerRepository.deleteById(id);
    }
    public List<Practitionner> getAllPractitionners() {
        return practitionerRepository.findAll();
    }
}

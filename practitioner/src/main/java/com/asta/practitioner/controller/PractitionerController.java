package com.asta.practitioner.controller;

import com.asta.practitioner.model.Practitionner;
import com.asta.practitioner.service.PractitionnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/practitioner")
public class PractitionerController {
    @Autowired
    private PractitionnerService practitionerService;

    @PostMapping("/new")
    public Practitionner addPractitioner(@RequestBody Practitionner practitioner){
        return practitionerService.createPractitioner(practitioner);
    }
    @GetMapping(("/get"))
    public Practitionner getPractitionerById(@RequestBody Practitionner practitioner){
        return practitionerService.getPractitionerById(practitioner.getId());
    }
    @PostMapping("/update")
    public Practitionner updatePractitioner(@RequestBody Practitionner practitioner){
        return practitionerService.updatePractitioner(practitioner);
    }
    @DeleteMapping("/delete")
    public void deletePractitioner(@RequestBody Practitionner practitioner){
        practitionerService.deletePractitioner(practitioner.getId());
    }
    @GetMapping("/all")
    public Iterable<Practitionner> getAllPractitionners(){
        return practitionerService.getAllPractitionners();
    }
}

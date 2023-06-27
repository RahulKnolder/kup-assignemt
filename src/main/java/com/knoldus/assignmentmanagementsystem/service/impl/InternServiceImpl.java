package com.knoldus.assignmentmanagementsystem.service.impl;

import com.knoldus.assignmentmanagementsystem.exception.ResourceNotFoundException;
import com.knoldus.assignmentmanagementsystem.model.Intern;
import com.knoldus.assignmentmanagementsystem.repository.InternRepository;
import com.knoldus.assignmentmanagementsystem.service.InternService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InternServiceImpl implements InternService {

    @Autowired
    private InternRepository internRepository;

    @Override
    public List<Intern> getAll() {
        return internRepository.findAll();
    }

    @Override
    public Intern addIntern(Intern intern) {
        return internRepository.save(intern);
    }


    @Override
    public String updateIntern(Intern intern, Integer internId) {
        Intern existingIntern = internRepository.findById(internId).orElseThrow(() -> new ResourceNotFoundException("Intern not found with InternId " + internId));
        existingIntern.setInternId(intern.getInternId());
        existingIntern.setName(intern.getName());
        existingIntern.setStudio(intern.getStudio());
        return "Updated Record of Intern";
    }

    @Override
    public String deleteIntern(Integer internId) {
        Optional<Intern> id = internRepository.findById(internId);
        if (id.isPresent()) {
            internRepository.deleteById(internId);
            return "Deleted Record of Intern with Id = " + internId;
        } else {
            throw new ResourceNotFoundException("Intern not found with InternId" + internId);
        }
    }


    @Override
    public Optional<Intern> getDetails(Integer internId) {
        return internRepository.findById(internId);
    }


}

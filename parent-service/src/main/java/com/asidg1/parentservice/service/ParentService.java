package com.asidg1.parentservice.service;

//import com.iStudent.repository.StudentRepository;
import com.asidg1.parentservice.model.DTOs.ParentDTO;
import com.asidg1.parentservice.model.entity.Parent;
import com.asidg1.parentservice.repository.ParentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentService {

    private final ParentRepository parentRepository;

    //private final StudentRepository studentRepository;

    //private final TownService townService;

    private final ModelMapper mapper;

    @Autowired
    public ParentService(ParentRepository parentRepository, /*StudentRepository studentRepository, TownService townService,*/ ModelMapper mapper) {
        this.parentRepository = parentRepository;
        //this.studentRepository = studentRepository;
        //this.townService = townService;
        this.mapper = mapper;
    }

    public Parent findParentById(Long parentId) {
        return parentRepository.findById(parentId).orElse(null);
    }

    public List<ParentDTO> getAllParents() {
        return parentRepository.
                findAll().
                stream().
                map(this::mapToParentDTO).
                toList();
    }

    private ParentDTO mapToParentDTO(Parent parent) {
        return mapper.map(parent, ParentDTO.class);
    }

    public Optional<ParentDTO> getParentById(Long parentId) {
        return parentRepository.
                findById(parentId).
                map(this::mapToParentDTO);
    }

    public long addParent(ParentDTO parentDTO) {
        //Town townToMap = townService.findByTownId(parentDTO.getTown().getId());

        Parent parent = mapper.map(parentDTO, Parent.class);
       // parent.setTown(townToMap);

        parentRepository.save(parent);

        //ParentDTO ptd = mapToParentDTO(parent);
        return parent.getId();

    }

    /*public Optional<StudentDTO> assignParentToStudent(Long parentId, Long studentId) {
        Optional<Parent> parentOptional = parentRepository.findById(parentId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (parentOptional.isPresent() && studentOptional.isPresent()) {
            Parent parent = parentOptional.get();
            Student student = studentOptional.get();

            student.setParent(parent);
            studentRepository.save(student);

            return studentRepository.findById(studentId).map(s -> mapper.map(s, StudentDTO.class));

        } else {

            return Optional.empty();
        }


    }*/

    public void deleteParentById(Long parentId) {
        Optional<Parent> pareOptional = parentRepository.findById(parentId);
        parentRepository.deleteById(parentId);
    }
}

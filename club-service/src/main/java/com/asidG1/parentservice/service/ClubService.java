package com.asidG1.parentservice.service;

import com.asidG1.parentservice.repository.ClubRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asidG1.parentservice.model.DTOs.ClubDTO;
import com.asidG1.parentservice.model.entity.Club;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository clubRepository;


    private final ModelMapper mapper;

    @Autowired
    public ClubService(ClubRepository clubRepository, /*StudentRepository studentRepository,*/ ModelMapper mapper) {
        this.clubRepository = clubRepository;
        //this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Transactional
    public List<ClubDTO> getAllClubs() {
        return clubRepository.
                findAll().
                stream().
                map(this::mapToClubDTO).
                toList();
    }

    private ClubDTO mapToClubDTO(Club club) {
        return mapper.map(club, ClubDTO.class);
    }

    @Transactional
    public Optional<ClubDTO> getClubById(Long clubId) {
        return clubRepository.
                findById(clubId).
                map(this::mapToClubDTO);
    }

    @Transactional
    public void deleteClubById(Long clubId) {
        Optional<Club> optionalClub = clubRepository.findById(clubId);


        if (optionalClub.isPresent()){
            Club club = optionalClub.get();
            /*Optional<List<Student>> studentsOptional = studentRepository.findByClubs(club);
            for (Student student : studentsOptional.get()){
                student.removeClub(club);
            }*/
        }

        clubRepository.deleteById(clubId);
    }

    public long addClub(ClubDTO clubDTO) {
        Club club = mapper.map(clubDTO, Club.class);

        clubRepository.save(club);

        return club.getId();
    }

    @Transactional
    public ClubDTO addStudentToClub(Long clubId, Long studentId) {
        Optional<Club> optionalClub = clubRepository.findById(clubId);

        if (optionalClub.isPresent()) {
            Club club = optionalClub.get();

            club.addStudentToClub(studentId);

            return mapper.map(club, ClubDTO.class);

        } else {
            return null;
        }

    }

    @Transactional
    public ClubDTO removeStudentFromClub(Long clubId, Long studentId) {
        Optional<Club> optionalClub = clubRepository.findById(clubId);

        if (optionalClub.isPresent()) {
            Club club = optionalClub.get();

            club.removeStudentFromClub(studentId);

            return mapper.map(club, ClubDTO.class);

        } else {
            return null;
        }

    }

    public ClubDTO changeClubName(Long id, ClubDTO clubDTO) {
        Optional<Club> optionalClub = clubRepository.findById(id);

        if (optionalClub.isPresent()) {
            Club club = optionalClub.get();
            club.setName(clubDTO.getName());

            clubRepository.save(club);
            return mapToClubDTO(club);
        }

        return null;
    }
}

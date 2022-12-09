package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.Lecturer;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.DuplicateEmailException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.ILecturerController;
import de.digitra.uniplaner.service.LecturerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/lecturers")
public class LecturerController implements ILecturerController {
    LecturerService lecturerService;
    Logger logger = LoggerFactory.getLogger(LecturerService.class);

    @GetMapping
    public ResponseEntity<Lecturer> createLecturer(@RequestBody Lecturer lecturer) throws BadRequestException, DuplicateEmailException{
        return ResponseEntity.created(URI.create("/lecturers/"+lecturer.getFirstName()+lecturer.getLastName())).build();
    }

    @GetMapping
    public ResponseEntity<Lecturer> updateLecturer(@RequestBody Lecturer lecturer) throws BadRequestException{
        logger.debug("Request to save Lecturer {}", lecturer);
        return ResponseEntity.ok(lecturerService.save(lecturer));
    }

    @GetMapping
    public ResponseEntity<Lecturer> updateLecturer(@PathVariable(value = "id") Long id, @Valid @RequestBody Lecturer lecturerDetails) throws ResourceNotFoundException{
        logger.debug("Request to save Lecturer {}", id, lecturerDetails);
        return ResponseEntity.ok(lecturerService.save(lecturerDetails));
    }

    @GetMapping
    public ResponseEntity<List<Lecturer>> getAlllecturers(){
        logger.debug("REST request to get all lectures");
        return ResponseEntity.ok(lecturerService.findAll());
    }

    @GetMapping
    public ResponseEntity<Optional<Lecturer>> getLecturer(@PathVariable Long id) throws ResourceNotFoundException{
        logger.debug("Request to find Lecture {}", id);
        return ResponseEntity.ok(lecturerService.findOne(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLecturer(@PathVariable Long id){
        logger.debug("Request to delete Lecture {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

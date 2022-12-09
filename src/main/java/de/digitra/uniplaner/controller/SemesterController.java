package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.Semester;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.ISemesterController;
import de.digitra.uniplaner.service.SemesterService;
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
@RequestMapping("/semesters")
public class SemesterController implements ISemesterController {

    SemesterService semesterService;
    Logger logger = LoggerFactory.getLogger(SemesterService.class);

    @GetMapping
    public ResponseEntity<Semester> createSemester(@RequestBody Semester semester) throws BadRequestException{
        return ResponseEntity.created(URI.create("/semesters/"+semester.getName())).build();
    }
    @GetMapping
    public ResponseEntity<Semester> updateSemester(@RequestBody Semester semester) throws BadRequestException{
        logger.debug("Request to save Lecture {}", semester);
        return ResponseEntity.ok(semesterService.save(semester));
    }
    public ResponseEntity<Semester> updateSemester(@PathVariable(value = "id") Long id, @Valid @RequestBody Semester semesterDetails) throws ResourceNotFoundException{
        logger.debug("Request to save Lecture {}", semesterDetails);
        return ResponseEntity.ok(semesterService.save(semesterDetails));
    }
    public ResponseEntity<List<Semester>> getAllsemesters(){
        logger.debug("REST request to get all lectures");
        return ResponseEntity.ok(semesterService.findAll());
    }
    public ResponseEntity<Optional<Semester>> getSemester(@PathVariable Long id) throws ResourceNotFoundException{
        logger.debug("Request to find Lecture {}", id);
        return ResponseEntity.ok(semesterService.findOne(id));
    }
    public ResponseEntity<Void> deleteSemester(@PathVariable Long id){
        logger.debug("Request to delete Lecture {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

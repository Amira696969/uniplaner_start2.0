package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.LectureDate;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.ILectureDateController;
import de.digitra.uniplaner.service.LectureDateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/lecturedates")
public class LectureDateController implements ILectureDateController {

    LectureDateService lectureDateService;
    Logger logger = LoggerFactory.getLogger(LectureDateService.class);

    @GetMapping
    public ResponseEntity<LectureDate> createLectureDate(@RequestBody LectureDate lecturedate) throws BadRequestException{
        return ResponseEntity.created(URI.create("/startDate/"+lecturedate.getStartDate())).build();
    }

    @GetMapping
    public ResponseEntity<LectureDate> updateLectureDate(@RequestBody LectureDate lecturedate) throws BadRequestException{
        logger.debug("Request to save Lecture {}", lecturedate);
        return ResponseEntity.ok(lectureDateService.save(lecturedate));
    }

    @GetMapping
    public ResponseEntity<LectureDate> updateLectureDate(@PathVariable(value = "id") Long id, @Valid @RequestBody LectureDate lecturedateDetails) throws ResourceNotFoundException{
        logger.debug("Request to save Lecture {}", id, lecturedateDetails);
        return ResponseEntity.ok(lectureDateService.save(lecturedateDetails));
    }

    @GetMapping
    public ResponseEntity<List<LectureDate>> getAlllecturedates(){
        logger.debug("Request to get all LectureDate");
        return ResponseEntity.ok(lectureDateService.findAll());
    }

    @GetMapping
    public ResponseEntity<Optional<LectureDate>> getLectureDate(@PathVariable Long id) throws ResourceNotFoundException{
        logger.debug("Request to find Lecture {}", id);
        return ResponseEntity.ok(lectureDateService.findOne(id));
    }

    @GetMapping
    public ResponseEntity<Void> deleteLectureDate(@PathVariable Long id){
        logger.debug("Request to delete Lecture {}", id);
        return null;
    }
}

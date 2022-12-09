package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.Lecture;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.ILectureController;
import de.digitra.uniplaner.interfaces.ILectureService;
import de.digitra.uniplaner.service.LectureService;
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
@RequestMapping("/lectures")
public class LectureController implements ILectureController {
    LectureService lectureService;
    Logger logger = LoggerFactory.getLogger(LectureService.class);


    @GetMapping
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture) throws BadRequestException{
        return ResponseEntity.created(URI.create("/lecture/"+lecture.getLectureName())).build();
    }

    @GetMapping
    public ResponseEntity<Lecture> updateLecture(@RequestBody Lecture lecture) throws BadRequestException{
        logger.debug("Request to save Lecture {}", lecture);
        return ResponseEntity.ok(lectureService.save(lecture));
    }

    @GetMapping
    public ResponseEntity<Lecture> updateLecture(@PathVariable(value = "id") Long id, @Valid @RequestBody Lecture lectureDetails) throws ResourceNotFoundException{
        logger.debug("Request to save Lecture {}", lectureDetails);
        return ResponseEntity.ok(lectureService.save(lectureDetails));
    }

    @GetMapping
    public ResponseEntity<List<Lecture>> getAlllectures(){
        logger.debug("REST request to get all lectures");
        return ResponseEntity.ok(lectureService.findAll());
    }

    @GetMapping
    public ResponseEntity<Optional<Lecture>> getLecture(@PathVariable Long id) throws ResourceNotFoundException{
        logger.debug("Request to find Lecture {}", id);
        return ResponseEntity.ok(lectureService.findOne(id));
    }

    @GetMapping
    public ResponseEntity<Void> deleteLecture(@PathVariable Long id){
        logger.debug("Request to delete Lecture {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.StudyClass;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.IStudyClassController;
import de.digitra.uniplaner.service.LectureService;
import de.digitra.uniplaner.service.StudyClassService;
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
@RequestMapping("/studyclasss")
public class StudyClassController implements IStudyClassController {
    StudyClassService studyClassService;
    Logger logger = LoggerFactory.getLogger(LectureService.class);

    public ResponseEntity<StudyClass> createStudyClass(@RequestBody StudyClass studyclass) throws BadRequestException{
        return ResponseEntity.created(URI.create("/studyclasss/"+studyclass.getName())).build();
    }

    public ResponseEntity<StudyClass> updateStudyClass(@RequestBody StudyClass studyclass) throws BadRequestException{
        logger.debug("Request to save Lecture {}", studyclass);
        return ResponseEntity.ok(studyClassService.save(studyclass));
    }

    public ResponseEntity<StudyClass> updateStudyClass(@PathVariable(value = "id") Long id, @Valid @RequestBody StudyClass studyclassDetails) throws ResourceNotFoundException{
        logger.debug("Request to save Lecture {}", studyclassDetails);
        return ResponseEntity.ok(studyClassService.save(studyclassDetails));
    }

    public ResponseEntity<List<StudyClass>> getAllstudyclasss(){
        logger.debug("REST request to get all lectures");
        return ResponseEntity.ok(studyClassService.findAll());
    }
    public ResponseEntity<Optional<StudyClass>> getStudyClass(@PathVariable Long id) throws ResourceNotFoundException{
        logger.debug("Request to find Lecture {}", id);
        return ResponseEntity.ok(studyClassService.findOne(id));
    }
    public ResponseEntity<Void> deleteStudyClass(@PathVariable Long id){
        logger.debug("Request to delete Lecture {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

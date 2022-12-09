package de.digitra.uniplaner.controller;

import de.digitra.uniplaner.domain.StudyProgram;
import de.digitra.uniplaner.exceptions.BadRequestException;
import de.digitra.uniplaner.exceptions.ResourceNotFoundException;
import de.digitra.uniplaner.interfaces.IStudyProgramController;
import de.digitra.uniplaner.service.LectureService;
import de.digitra.uniplaner.service.StudyProgramService;
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
@RequestMapping("/studyprograms")
public class StudyProgramController implements IStudyProgramController {
    StudyProgramService studyProgramService;
    Logger logger = LoggerFactory.getLogger(LectureService.class);

    public ResponseEntity<StudyProgram> createStudyProgram(@RequestBody StudyProgram studyprogram) throws BadRequestException{
        return ResponseEntity.created(URI.create("/studyprograms/"+studyprogram.getName())).build();

    }

    public ResponseEntity<StudyProgram> updateStudyProgram(@RequestBody StudyProgram studyprogram) throws BadRequestException{
        logger.debug("Request to save Lecture {}", studyprogram);
        return ResponseEntity.ok(studyProgramService.save(studyprogram));
    }

    public ResponseEntity<StudyProgram> updateStudyProgram(@PathVariable(value = "id") Long id, @Valid @RequestBody StudyProgram studyprogramDetails) throws ResourceNotFoundException{
        logger.debug("Request to save Lecture {}", studyprogramDetails);
        return ResponseEntity.ok(studyProgramService.save(studyprogramDetails));
    }
    public ResponseEntity<List<StudyProgram>> getAllstudyprograms(){
        logger.debug("REST request to get all lectures");
        return ResponseEntity.ok(studyProgramService.findAll());
    }
    public ResponseEntity<Optional<StudyProgram>> getStudyProgram(@PathVariable Long id) throws ResourceNotFoundException{
        logger.debug("Request to find Lecture {}", id);
        return ResponseEntity.ok(studyProgramService.findOne(id));
    }
    public ResponseEntity<Void> deleteStudyProgram(@PathVariable Long id){
        logger.debug("Request to delete Lecture {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

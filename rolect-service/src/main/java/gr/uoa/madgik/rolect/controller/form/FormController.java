package gr.uoa.madgik.rolect.controller.form;


import gr.uoa.madgik.rolect.model.schema.Question;
import gr.uoa.madgik.rolect.model.schema.Schema;
import gr.uoa.madgik.rolect.model.schema.Section;
import gr.uoa.madgik.rolect.model.schema.Vocabulary;
import gr.uoa.madgik.rolect.service.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FormController {

    @Autowired
    SchemaService schemaService;

    @GetMapping("/schema")
    public ResponseEntity<Schema> getSchema(){
        return ResponseEntity.ok(schemaService.getSchema());
    }

    @GetMapping("/sections")
    public ResponseEntity<List<Section>> getSections(){
        return ResponseEntity.ok(schemaService.getSections());
    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getQuestions(){
        return ResponseEntity.ok(schemaService.getQuestions());
    }

    @GetMapping("/vocabularies")
    public ResponseEntity<List<Vocabulary>> getVocabularies(){
        return ResponseEntity.ok(schemaService.getVocabularies());
    }

}

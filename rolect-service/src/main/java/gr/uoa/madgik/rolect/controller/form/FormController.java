package gr.uoa.madgik.rolect.controller.form;


import gr.uoa.madgik.rolect.model.form.Question;
import gr.uoa.madgik.rolect.model.form.Schema;
import gr.uoa.madgik.rolect.model.form.Section;
import gr.uoa.madgik.rolect.model.form.Vocabulary;
import gr.uoa.madgik.rolect.pdf.PdfService;
import gr.uoa.madgik.rolect.service.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
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

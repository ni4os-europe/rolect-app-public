package gr.uoa.madgik.rolect.service;

import gr.uoa.madgik.rolect.model.form.Question;
import gr.uoa.madgik.rolect.model.form.Schema;
import gr.uoa.madgik.rolect.model.form.Section;
import gr.uoa.madgik.rolect.model.form.Vocabulary;
import gr.uoa.madgik.rolect.repository.SchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemaService {

    @Autowired
    SchemaRepository schemaRepository;

    public Schema getSchema(){
        return schemaRepository.findAll().get(0);
    }

    public List<Section> getSections(){
        return schemaRepository.findSections().get(0).getSections();
    }

    public List<Question> getQuestions(){
        return schemaRepository.findQuestions().get(0).getQuestions();
    }

    public List<Vocabulary> getVocabularies() {
        return schemaRepository.findVocabularies().get(0).getVocabularies();
    }

}

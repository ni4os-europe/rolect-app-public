package gr.uoa.madgik.rolect.repository;

import gr.uoa.madgik.rolect.model.schema.Schema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchemaRepository extends MongoRepository<Schema, Long> {

    @Query(value = "{}", fields = "{'sections':1,'_id':0}")
    List<Schema> findSections();

    @Query(value = "{}", fields = "{'questions':1,'_id':0}")
    List<Schema> findQuestions();

    @Query(value = "{}", fields = "{'vocabularies':1,'_id':0}")
    List<Schema> findVocabularies();
}

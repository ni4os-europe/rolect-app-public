package gr.uoa.madgik.rolect.repository;

import gr.uoa.madgik.rolect.model.assessment.Response;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends MongoRepository<Response,String> {

}

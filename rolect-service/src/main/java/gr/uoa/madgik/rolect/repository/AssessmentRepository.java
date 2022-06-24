package gr.uoa.madgik.rolect.repository;

import gr.uoa.madgik.rolect.model.assessment.Assessment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssessmentRepository extends CrudRepository<Assessment,Long> {

    List<Assessment> findAllByUserId(Long userId);

    Optional<Assessment> findByUuid(String uuid);
}

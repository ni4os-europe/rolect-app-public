package gr.uoa.madgik.rolect.repository;

import gr.uoa.madgik.rolect.model.misc.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findByName(String name);
}

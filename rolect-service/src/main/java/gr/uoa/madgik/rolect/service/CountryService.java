package gr.uoa.madgik.rolect.service;

import gr.uoa.madgik.rolect.model.misc.Country;
import gr.uoa.madgik.rolect.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Country getCountryByName(String name){
        return this.countryRepository.findByName(name);
    }

    public List<Country> getAllCountries(){
        List<Country> countries = new ArrayList<>();
        this.countryRepository.findAll().forEach(countries::add);
        return countries;
    }

    public void saveCountry(Country country){
        this.countryRepository.save(country);
    }
}

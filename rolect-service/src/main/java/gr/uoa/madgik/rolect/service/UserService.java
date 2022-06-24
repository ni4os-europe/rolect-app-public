package gr.uoa.madgik.rolect.service;

import gr.uoa.madgik.rolect.model.auth.Role;
import gr.uoa.madgik.rolect.model.auth.User;
import gr.uoa.madgik.rolect.repository.CountryRepository;
import gr.uoa.madgik.rolect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    CountryService countryService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRepository userRepository;


    public gr.uoa.madgik.rolect.model.auth.User createUser(OidcUserInfo oidcUserInfo){

        User user = new User();

        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
//        user.setUsername(oidcUserInfo.getPreferredUsername());
        user.setUsername(oidcUserInfo.getEmail());
        user.setEmail(oidcUserInfo.getEmail());
        user.setFirstName(oidcUserInfo.getGivenName());
        user.setLastName(oidcUserInfo.getFamilyName());
        user.setCountry(countryService.getCountryByName(oidcUserInfo.getAddress().getCountry()));
        user.setPosition(oidcUserInfo.getAddress().getLocality());
        user.setAddress(oidcUserInfo.getAddress().getStreetAddress());
        user.setInstitutionName(oidcUserInfo.getClaim("eduperson_entitlement"));
        user.setActive(true);
        Role role = roleService.findByName("ROLE_USER").orElseThrow(
                RuntimeException::new
        );
        user.setRoles(new HashSet<Role>(){{add(role);}});
        return userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }


    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public User findById(Long id) throws Exception {

        return userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User with id: \"" + id + "\" was not found.")
        );
    }
}

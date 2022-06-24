package gr.uoa.madgik.rolect.security;


import gr.uoa.madgik.rolect.model.auth.User;
import gr.uoa.madgik.rolect.repository.UserRepository;
import gr.uoa.madgik.rolect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    private UserPrincipalService userPrincipalService;

    @Autowired
    private UserService userService;


    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

        // Fetch Oidc user
        OidcUser oidcUser = super.loadUser(userRequest);

        // Return UserPrincipal
        try {
//            System.out.println(oidcUser.getUserInfo().getAddress());
//            System.out.println(oidcUser.getUserInfo().getEmail());
//            System.out.println(oidcUser.getUserInfo().getBirthdate());
//            System.out.println(oidcUser.getUserInfo().getEmailVerified());
//            System.out.println(oidcUser.getUserInfo().getGender());
//            System.out.println(oidcUser.getUserInfo().getFamilyName());
//            System.out.println(oidcUser.getUserInfo().getFullName());
//            System.out.println(oidcUser.getUserInfo().getGivenName());
//            System.out.println(oidcUser.getUserInfo().getLocale());
//            System.out.println(oidcUser.getUserInfo().getPicture());
//            System.out.println(oidcUser.getUserInfo().getPhoneNumber());
//            System.out.println(oidcUser.getUserInfo().getPhoneNumberVerified());
//            System.out.println(oidcUser.getUserInfo().getBirthdate());
//            System.out.println(oidcUser.getUserInfo().getAddress().getCountry());
//            System.out.println(oidcUser.getUserInfo().getAddress().getPostalCode());
//            System.out.println(oidcUser.getUserInfo().getAddress().getStreetAddress());
            return processOidcUser(oidcUser);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the CustomFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * @param oidcUser Oidc user of current security context
     * @return UserPrincipal for the given oidcUser
     */

    public OidcUser processOidcUser(OidcUser oidcUser) {

        UserDetails userDetails = userPrincipalService.loadUserByUsername(oidcUser.getEmail());


        // Insert new user to the DB (if not exist)
        if (userDetails == null){
            System.out.println("------------------------------------------------");
            System.out.println("User does not exist. Inserting into database....");
            System.out.println("------------------------------------------------\n");
            userDetails = UserPrincipal.create(userService.createUser(oidcUser.getUserInfo()), oidcUser);
        }
        else{
            System.out.println("------------------------------------------------");
            System.out.println("User already in database");
            System.out.println("------------------------------------------------\n");
        }

        System.out.println("===========================================");
        System.out.println(Arrays.toString(userDetails.getAuthorities().toArray()));
        System.out.println("===========================================");

        return  (UserPrincipal)userDetails;
    }
}

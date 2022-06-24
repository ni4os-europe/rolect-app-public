package gr.uoa.madgik.rolect.service;


import gr.uoa.madgik.rolect.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public UserPrincipal getPrincipal(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal principal = ((UserPrincipal) authentication.getPrincipal());
            System.out.println(principal);
            return principal;
        }
        else
        {
            System.out.println("Unauthenticated user\n\n");
            return null;
        }
    }
}

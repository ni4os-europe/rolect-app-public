package gr.uoa.madgik.rolect.security;

import gr.uoa.madgik.rolect.model.auth.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserPrincipal implements OidcUser, UserDetails {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;
    private OidcUserInfo oidcUserInfo;
    private OidcIdToken oidcIdToken;
    private Map<String, Object> claims;

    public UserPrincipal(Long id, String email, String password, String firstName, String lastName, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());

//        List<GrantedAuthority> authorities = Collections.
//                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                null,
                user.getFirstName(),
                user.getLastName(),
                authorities
        );
    }

    public static UserPrincipal create(User user, OidcUser oidcUser) {
        UserPrincipal userPrincipal = create(user);
        userPrincipal.setAttributes(oidcUser.getAttributes());
        userPrincipal.setAuthorities(oidcUser.getAuthorities());
        userPrincipal.setOidcUserInfo(oidcUser.getUserInfo());
        userPrincipal.setOidcIdToken(oidcUser.getIdToken());
        userPrincipal.setClaims(oidcUser.getClaims());
        return userPrincipal;

    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", attributes=" + attributes +
                ", authorities=" + authorities +
                ", oidcUserInfo=" + oidcUserInfo +
                ", oidcIdToken=" + oidcIdToken +
                ", claims=" + claims +
                '}';
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getEmail() {

        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getClaims() {
        return claims;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return oidcUserInfo;
    }

    @Override
    public OidcIdToken getIdToken() {
        return oidcIdToken;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public void setOidcUserInfo(OidcUserInfo oidcUserInfo) {
        this.oidcUserInfo = oidcUserInfo;
    }

    public void setOidcIdToken(OidcIdToken oidcIdToken) {
        this.oidcIdToken = oidcIdToken;
    }

    public void setClaims(Map<String, Object> claims) {
        this.claims = claims;
    }
}

package gr.uoa.madgik.rolect;

import gr.uoa.madgik.rolect.model.auth.Role;
import gr.uoa.madgik.rolect.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RolectApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RolectApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RolectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Insert roles in our DB
        Role userRole = roleService.saveRole("ROLE_USER");
        Role adminRole = roleService.saveRole("ROLE_ADMIN");
    }
}

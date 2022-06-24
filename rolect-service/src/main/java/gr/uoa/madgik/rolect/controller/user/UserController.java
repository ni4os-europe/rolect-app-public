package gr.uoa.madgik.rolect.controller.user;

import gr.uoa.madgik.rolect.model.assessment.Assessment;
import gr.uoa.madgik.rolect.model.auth.User;
import gr.uoa.madgik.rolect.security.CurrentUser;
import gr.uoa.madgik.rolect.security.UserPrincipal;
import gr.uoa.madgik.rolect.service.AssessmentService;
import gr.uoa.madgik.rolect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AssessmentService assessmentService;

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) throws Exception {
        return ResponseEntity.ok(userService.findById(userPrincipal.getId()));
    }


    @GetMapping("/all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAll() throws Exception {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/user/assessments")
    public ResponseEntity<List<Assessment>> getAssessments(@RequestParam("id") Long id){
        return ResponseEntity.ok(assessmentService.getAssessments(id));
    }

    @PostMapping("/user/update")
    public void updateUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @DeleteMapping(value = "/user/assessments/delete")
    public void deleteClearance(@RequestParam("id") Long id) {
        assessmentService.deleteAssessment(id);
    }


}

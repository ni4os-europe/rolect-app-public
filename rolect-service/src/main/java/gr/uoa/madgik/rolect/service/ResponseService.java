package gr.uoa.madgik.rolect.service;


import gr.uoa.madgik.rolect.model.assessment.Response;
import gr.uoa.madgik.rolect.model.submitted_form.FormResponse;
import gr.uoa.madgik.rolect.model.submitted_form.SubmittedForm;
import gr.uoa.madgik.rolect.repository.ResponseRepository;
import gr.uoa.madgik.rolect.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    AuthService authService;

    public Response submitResponse(SubmittedForm submittedForm){
        if (submittedForm.getResponseId() != null){
            return updateResponse(submittedForm);

        }
        else return saveResponse(submittedForm);
    }

    public Response saveResponse(SubmittedForm submittedForm){

        Response response = new Response();
        UserPrincipal userPrincipal = authService.getPrincipal();

        if(userPrincipal != null){
            response.setUserId(userPrincipal.getId());
        }
        response.setCreatedAt(new Date());
        response.setUpdatedAt(new Date());
        response.setResourceUrl(submittedForm.getResourceUrl());
        response.setAnswers(submittedForm.getAnswers());
        response.setStatus(submittedForm.getStatus());
        return responseRepository.save(response);
    }

    public Response updateResponse(SubmittedForm submittedForm){
        Response response = getResponseById(submittedForm.getResponseId());
        response.setUpdatedAt(new Date());
        response.setResourceUrl(submittedForm.getResourceUrl());
        response.setAnswers(submittedForm.getAnswers());
        response.setStatus(submittedForm.getStatus());
        return responseRepository.save(response);
    }

    public Response getResponseById(String id){
        return responseRepository.findById(id).orElse(null);
    }

    public void deleteById(String id){
        responseRepository.deleteById(id);
    }
}

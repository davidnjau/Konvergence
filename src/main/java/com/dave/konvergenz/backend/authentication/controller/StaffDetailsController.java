package com.dave.konvergenz.backend.authentication.controller;

import com.dave.konvergenz.ErrorMessage;
import com.dave.konvergenz.Results;
import com.dave.konvergenz.backend.authentication.entity.StaffDetails;
import com.dave.konvergenz.backend.authentication.service_class.impl.StaffDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class StaffDetailsController {

    @Autowired
    private StaffDetailsServiceImpl staffDetailsService;

//    @GetMapping
//    public Map<String, String> home()
//    {
//        return Collections.singletonMap("name", "JSBLOGS");
//    }

    @RequestMapping(value = "/api/v1/auth/registration", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody StaffDetails StaffDetails){

        Results addedResults = staffDetailsService.registerUser(StaffDetails);
        if (addedResults != null){

            var statusCode = addedResults.getStatusCode();
            var results = addedResults.getDetails();

            if (statusCode == 201){
                return new ResponseEntity(results, HttpStatus.CREATED);
            }else {
                var errorMessage = results.toString();
                return ResponseEntity.badRequest().body(new ErrorMessage(errorMessage));
            }

        }else {
            return ResponseEntity.internalServerError().body(new ErrorMessage("There was an error with your request."));
        }

    }

    @RequestMapping(value = "/api/v1/users/get-users/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllStaff(){

        List<StaffDetails> usersList = staffDetailsService.getAllUsers();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(usersList,headers, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/v1/users/get-users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getStaffDetails(@PathVariable("userId") String userId){

        StaffDetails StaffDetails = staffDetailsService.getStaffDetails(userId);
        if (StaffDetails != null){
            return new ResponseEntity(StaffDetails, HttpStatus.CREATED);
        }else {
            return ResponseEntity.badRequest().body(new ErrorMessage("No user has been found"));
        }

    }
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }


}

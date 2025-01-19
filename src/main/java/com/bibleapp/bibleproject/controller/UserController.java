package com.bibleapp.bibleproject.controller;

import com.bibleapp.bibleproject.Reference;
import com.bibleapp.bibleproject.entity.AuthRequest;
import com.bibleapp.bibleproject.entity.UserInfo;
import com.bibleapp.bibleproject.service.JwtService;
import com.bibleapp.bibleproject.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private List<Reference> referenceList = new ArrayList<>();

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        System.out.println("here");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    private boolean isExisting(Reference reference) {
        boolean foundRef = false;
        for (Reference item : referenceList) {
            if ((item.getBook().equals(reference.getBook())) &&
                    (item.getChapter().equals(reference.getChapter())) &&
                    (item.getVerse().equals(reference.getVerse()))) {
                System.out.print("dupe");
                foundRef = true;
                break;
            }
        }

        return foundRef;
    }

    @PostMapping(path = "/user/add-verse-jwt")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> addVerse(@RequestBody Reference reference) {

        if(isExisting(reference)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\": \"Reference already exists.\"}");
        }else {
            referenceList.add(reference);

            return ResponseEntity.ok("{\"message\": \" Reference added successfully.\" }");
        }

    }
}

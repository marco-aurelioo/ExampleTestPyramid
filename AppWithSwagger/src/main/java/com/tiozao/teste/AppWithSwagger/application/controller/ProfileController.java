package com.tiozao.teste.AppWithSwagger.application.controller;

import com.tiozao.teste.AppWithSwagger.application.controller.model.ProfileDto;
import com.tiozao.teste.AppWithSwagger.application.domain.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import javax.websocket.server.PathParam;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping("/{id_profile}")
    public ResponseEntity<ProfileDto> findProfile(@PathVariable("id_profile") String id_profile){
        return ResponseEntity.ok(service.findProfile(id_profile));
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(
            @RequestBody ProfileDto profileDto
            ){
        return ResponseEntity.ok(service.createProfile(profileDto));
    }

    @PutMapping("/{id_profile}")
    public ResponseEntity<ProfileDto> updateProfile(
            @PathParam("id_profile") String id_profile,
            @RequestBody ProfileDto profile_n ) throws InvalidAttributeValueException {
        return ResponseEntity.ok(service.updateProfile(id_profile,profile_n));
    }

    @DeleteMapping("/{id_profile}")
    public ResponseEntity<ProfileDto> delete(@PathParam("id_profile") String id_profile){
        service.deleteProfile(id_profile);
        return ResponseEntity.ok().build();
    }


}

package com.tiozao.teste.AppWithSwagger.application.controller;

import com.tiozao.teste.AppWithSwagger.application.controller.model.ProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController("/profile")
public class ProfileController {

    Map<String,ProfileDto> map_profile = new HashMap<String,ProfileDto>();

    @GetMapping("/{id_profile}")
    public ResponseEntity<ProfileDto> findProfile(@PathParam("id_profile") String id_profile){
        ProfileDto profile = map_profile.get(id_profile);
        if(profile!= null){
            return ResponseEntity.ok(profile);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(
            @RequestBody ProfileDto profileDto
            ){
        profileDto.setId(UUID.randomUUID().toString());
        map_profile.put(profileDto.getId(),profileDto);
        return ResponseEntity.ok(profileDto);
    }

    @PutMapping("/{id_profile}")
    public ResponseEntity<ProfileDto> updateProfile(
            @PathParam("id_profile") String id_profile,
            @RequestBody ProfileDto profile_n ){
        ProfileDto profile_s = map_profile.get(id_profile);
        if(profile_s == null){
            return ResponseEntity.notFound().build();
        }
        if(profile_s.getId() != profile_n.getId()){
            return ResponseEntity.unprocessableEntity().build();
        }
        map_profile.put(id_profile,profile_n);
        return ResponseEntity.ok(profile_n);
    }

    @DeleteMapping("/{id_profile}")
    public ResponseEntity<ProfileDto> delete(@PathParam("id_profile") String id_profile){
        if(map_profile.get(id_profile) != null){
            map_profile.remove(id_profile);
        }else{
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }


}

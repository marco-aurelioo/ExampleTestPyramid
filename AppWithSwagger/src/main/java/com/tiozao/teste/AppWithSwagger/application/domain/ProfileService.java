package com.tiozao.teste.AppWithSwagger.application.domain;

import com.tiozao.teste.AppWithSwagger.application.controller.model.ProfileDto;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.management.InvalidAttributeValueException;
import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ProfileService {

    Map<String,ProfileDto> map_profile = new HashMap<String,ProfileDto>();

    public ProfileDto findProfile(String id_profile){
       return getProfile(id_profile);
    }

    public ProfileDto createProfile( ProfileDto profileDto){
        profileDto.setId(UUID.randomUUID().toString());
        map_profile.put(profileDto.getId(),profileDto);
        return profileDto;
    }

    public ProfileDto updateProfile(String id_profile, ProfileDto profileDto){
        ProfileDto profile_old = getProfile(id_profile);
        if(profile_old.getId() == id_profile) {
            map_profile.put(id_profile, profileDto);
        }else{
            throw new IllegalArgumentException("profile incompativel com id.");
        }
        return profileDto;
    }

    private ProfileDto getProfile(String id_profile) {
        ProfileDto profile = map_profile.get(id_profile);
        if(profile == null){
            throw new NotFoundException("profile não encontrado! id '"+id_profile+"'");
        }
        return profile;
    }

    public void deleteProfile(String id_profile) {
        getProfile(id_profile);
        map_profile.remove(id_profile);
    }
}

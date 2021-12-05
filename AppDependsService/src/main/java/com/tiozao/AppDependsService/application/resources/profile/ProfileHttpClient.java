package com.tiozao.AppDependsService.application.resources.profile;


import com.tiozao.AppDependsService.application.resources.profile.model.ProfileServiceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profileService", url = "http://${profile.api.url}:${profile.api.port}/" )
public interface ProfileHttpClient {

    @GetMapping("/{id_profile}")
    ResponseEntity<ProfileServiceDto> findProfileById(@PathVariable("id_profile") String id_profile);

}

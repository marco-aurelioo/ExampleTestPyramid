package com.tiozao.AppDependsService.AppDependsService.application.resources.profile;


import com.tiozao.AppDependsService.AppDependsService.application.resources.profile.model.ProfileServiceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.PathParam;

@FeignClient(name = "profileService", url = "http://${profile.api.url}:${profile.api.port}/" )
public interface ProfileHttpClient {

    @GetMapping("/{id_profile}")
    ResponseEntity<ProfileServiceDto> findProfileById(@PathParam("id_profile") String id_profile);

}

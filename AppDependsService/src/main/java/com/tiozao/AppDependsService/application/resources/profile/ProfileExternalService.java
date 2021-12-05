package com.tiozao.AppDependsService.application.resources.profile;

import com.tiozao.AppDependsService.application.controller.model.ProfileDto;
import com.tiozao.AppDependsService.application.resources.assemble.ConvertProfileServiceToProfileDomain;
import org.springframework.stereotype.Service;

@Service
public class ProfileExternalService {

    private ProfileHttpClient httpClient;
    private ConvertProfileServiceToProfileDomain converter;

    public ProfileExternalService(
            ProfileHttpClient httpClient,
            ConvertProfileServiceToProfileDomain converter){
        this.httpClient = httpClient;
        this.converter = converter;
    }

    public ProfileDto findProfile(String idProfile){
        return converter.convertOrigin(
                httpClient.findProfileById(idProfile)
                        .getBody());
    }
}

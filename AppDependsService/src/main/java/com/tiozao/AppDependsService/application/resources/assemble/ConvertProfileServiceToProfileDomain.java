package com.tiozao.AppDependsService.application.resources.assemble;

import com.tiozao.AppDependsService.application.controller.model.ProfileDto;
import com.tiozao.AppDependsService.application.resources.profile.model.ProfileServiceDto;
import org.springframework.stereotype.Component;

@Component
public class ConvertProfileServiceToProfileDomain extends Converter<ProfileServiceDto, ProfileDto>{

    public ConvertProfileServiceToProfileDomain() {
        super(ConvertProfileServiceToProfileDomain::convertServiceToDomain, ConvertProfileServiceToProfileDomain::convertDomainToService);
    }

    public static ProfileDto convertServiceToDomain(ProfileServiceDto serviceDto){
        ProfileDto domainDto = new ProfileDto();
        domainDto.setName(serviceDto.getName());
        domainDto.setId(serviceDto.getId());
        return domainDto;
    }

    public static ProfileServiceDto convertDomainToService(ProfileDto domainDto){
        return null;//nao tem necessidade
    }

}

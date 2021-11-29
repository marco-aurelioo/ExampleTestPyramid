package com.tiozao.AppDependsService.AppDependsService.application.domain;

import com.tiozao.AppDependsService.AppDependsService.application.controller.model.BagDto;
import com.tiozao.AppDependsService.AppDependsService.application.controller.model.BagItem;
import com.tiozao.AppDependsService.AppDependsService.application.controller.model.ProfileDto;
import com.tiozao.AppDependsService.AppDependsService.application.resources.profile.ProfileExternalService;
import com.tiozao.AppDependsService.AppDependsService.application.resources.profile.ProfileHttpClient;
import com.tiozao.AppDependsService.AppDependsService.application.resources.profile.model.ProfileServiceDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BagServices {

    private ProfileExternalService profileService;

    private Map<String, BagDto> map_bags = new HashMap<>();

    public BagServices(ProfileExternalService profileService){
        this.profileService = profileService;
    }

    public BagItem addItemBag(String id_profile, BagItem item) {
        BagDto bag = map_bags.get(id_profile);
        if(bag == null){
            ProfileDto profile = profileService.findProfile(id_profile);
            bag = new BagDto();
            bag.setProfile(profile);
            bag.getItens().add(item);
            map_bags.put(id_profile,bag);
        }else{
            bag.getItens().add(item);
        }
        return item;
    }
}

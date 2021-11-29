package com.tiozao.AppDependsService.AppDependsService.application.controller.model;

import java.util.ArrayList;
import java.util.List;

public class BagDto {

    private ProfileDto profile;
    private List<BagItem> itens = new ArrayList<>();

    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }

    public List<BagItem> getItens() {
        return itens;
    }

    public void setItens(List<BagItem> itens) {
        this.itens = itens;
    }
}

package com.tiozao.teste.AppWithSwagger.application.domain;

import com.tiozao.teste.AppWithSwagger.application.controller.model.ProfileDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webjars.NotFoundException;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class ProfileServiceTest {

    @Autowired
    private ProfileService service = null;

    @Test
    public void createService(){
        //give

        ProfileDto profile = new ProfileDto();
        profile.setName("Teste");
        ProfileDto profile_create = service.createProfile(profile);

        assertThat(profile.getName()).isEqualTo(profile_create.getName());
        assertThat(profile_create.getId()).isNotNull();
    }

    @Test
    public void createAndFindService(){
        //give

        ProfileDto profile = new ProfileDto();
        profile.setName("Teste");
        ProfileDto profile_create = service.createProfile(profile);

        assertThat(profile.getName()).isEqualTo(profile_create.getName());
        assertThat(profile_create.getId()).isNotNull();

        ProfileDto profile_find = service.findProfile(profile_create.getId());
        assertThat(profile_find.getName()).isEqualTo(profile_create.getName());
        assertThat(profile_find.getId()).isEqualTo(profile_create.getId());

    }

    @Test
    public void findFindServiceNotFoud(){
        //give
        assertThatThrownBy(() -> service.findProfile(UUID.randomUUID().toString()))
           .isInstanceOf(NotFoundException.class);
    }

    @Test
    public void updateFindService(){

        ProfileDto profile = new ProfileDto();
        profile.setName("Teste");
        ProfileDto profile_create = service.createProfile(profile);

        assertThat(profile.getName()).isEqualTo(profile_create.getName());
        assertThat(profile_create.getId()).isNotNull();

        ProfileDto profile_find = service.findProfile(profile_create.getId());
        assertThat(profile_find.getName()).isEqualTo(profile_create.getName());
        assertThat(profile_find.getId()).isEqualTo(profile_create.getId());

        assertThat(profile_find.getNickName()).isNull();
        profile_find.setName("NovoTeste");
        profile_find.setNickName("NickiName");

        ProfileDto profile_update = service.updateProfile(profile_find.getId(), profile_find);

        assertThat(profile_update.getId()).isEqualTo(profile_find.getId());
        assertThat(profile_update.getNickName()).isEqualTo(profile_find.getNickName());

    }

    @Test
    public void updateFindServiceInvalid(){

        ProfileDto profile = new ProfileDto();
        profile.setName("Teste");
        ProfileDto profile_create = service.createProfile(profile);

        assertThat(profile.getName()).isEqualTo(profile_create.getName());
        assertThat(profile_create.getId()).isNotNull();

        ProfileDto profile_find = service.findProfile(profile_create.getId());
        assertThat(profile_find.getName()).isEqualTo(profile_create.getName());
        assertThat(profile_find.getId()).isEqualTo(profile_create.getId());

        assertThat(profile_find.getNickName()).isNull();


        assertThatThrownBy(() -> service.updateProfile(UUID.randomUUID().toString(), profile_find))
                .isInstanceOf(IllegalArgumentException.class);

    }


}
package com.tiozao.teste.AppWithSwagger;

import com.tiozao.teste.AppWithSwagger.application.controller.ProfileController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AppWithSwaggerApplicationTest {

    @Autowired
    private ProfileController controller;

    @Test
    public void contextTestLoad(){
        assertThat(controller).isNotNull();
    }

}
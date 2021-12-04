package com.tiozao.teste.AppWithSwagger.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiozao.teste.AppWithSwagger.application.controller.model.ProfileDto;
import com.tiozao.teste.AppWithSwagger.application.domain.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.webjars.NotFoundException;

import javax.management.InvalidAttributeValueException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfileService service;

    @InjectMocks
    private ProfileController controller;

    private JacksonTester<ProfileDto> jsonProfile;

    @BeforeEach
    public void setup(){
        JacksonTester.initFields(this, ObjectMapper::new);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerHandler())
                .build();
    }

    @Test
    public void canReadAnProfile() throws Exception {
        given(service.findProfile(anyString())).willReturn(new ProfileDto());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/id_profile")
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());

    }

    @Test
    public void cantReadAnProfile() throws Exception {
        given(service.findProfile(anyString())).willThrow(NotFoundException.class);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/id_profile")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentType()).isNull();

    }

    @Test
    public void canCreateAnProfile() throws Exception {

        given(service.createProfile(any(ProfileDto.class))).willReturn(new ProfileDto());

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/")
                        .content(jsonProfile.write(new ProfileDto()).getJson().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).isNotEmpty();

    }

    @Test
    public void canUpdateAnProfile() throws Exception {
        ProfileDto profile = new ProfileDto();

        given(service.updateProfile(anyString(),any(ProfileDto.class))).willReturn(profile);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put("/123456")
                        .content(jsonProfile.write(profile).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).isNotEmpty();

    }

    @Test
    public void cantUpdateAnProfile() throws Exception {

        given(service.updateProfile(any(String.class),any(ProfileDto.class))).willThrow(IllegalArgumentException.class);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put("/id_profile")
                        .content(jsonProfile.write(new ProfileDto()).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
        assertThat(response.getContentType()).isNull();
        assertThat(response.getContentAsString()).isEmpty();

    }
}
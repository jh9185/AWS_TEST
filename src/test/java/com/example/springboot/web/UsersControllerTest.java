package com.example.springboot.web;

import com.example.springboot.domain.posts.Posts;
import com.example.springboot.domain.posts.PostsRepository;
import com.example.springboot.domain.user.Role;
import com.example.springboot.domain.user.Users;
import com.example.springboot.domain.user.UsersRepository;
import com.example.springboot.web.dto.Posts.PostsSaveRequestDto;
import com.example.springboot.web.dto.Posts.PostsUpdateRequestDto;
import com.example.springboot.web.dto.Users.UsersUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        usersRepository.deleteAll();
    }
    @Test
    @WithMockUser(roles="USER")
    public void User_등록() throws Exception {
        //given
        Users savedUsers = usersRepository.save(Users.builder()
                .name("kjh")
                .email("test@test.com")
                .role(Role.USER)
                .region("서울")
                .build());

        //then
        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo("kjh");
        assertThat(all.get(0).getEmail()).isEqualTo("test@test.com");
        assertThat(all.get(0).getRegion()).isEqualTo("서울");
    }
    @Test
    @WithMockUser(roles="USER")
    public void User_Region_수정() throws Exception {
        //given
        Users savedUsers = usersRepository.save(Users.builder()
                .name("kjh")
                .email("test@test.com")
                .role(Role.USER)
                .region("서울")
                .build());

        Long updateId = savedUsers.getId();
        String expectedRegion = "인천";
        Users updateUsers = new Users(
                savedUsers.getName(), savedUsers.getEmail(),
                savedUsers.getPicture(), savedUsers.getRole(),
                expectedRegion
        );

        UsersUpdateRequestDto requestDto =
                UsersUpdateRequestDto.builder()
                        .entity(updateUsers)
                        .build();

        String url = "http://localhost:" + port + "/user/update/" + updateId;

        HttpEntity<UsersUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getRegion()).isEqualTo(expectedRegion);

    }
}

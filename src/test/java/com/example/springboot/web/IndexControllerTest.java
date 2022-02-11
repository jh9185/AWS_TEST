package com.example.springboot.web;

import com.example.springboot.Component.ApiComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩() {
        String body = this.restTemplate.getForObject("/", String.class);

        assertThat(body).contains("버스 도착 웹서비스");
    }

    @Test
    public void profile_테스트() {
        String profile = this.restTemplate.getForObject("/profile", String.class);

        assertThat(profile).contains("default");
    }
}

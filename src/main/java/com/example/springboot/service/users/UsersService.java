package com.example.springboot.service.users;

import com.example.springboot.domain.posts.Posts;
import com.example.springboot.domain.user.UsersRepository;
import com.example.springboot.domain.user.Users;
import com.example.springboot.web.dto.Posts.PostsResponseDto;
import com.example.springboot.web.dto.Posts.PostsUpdateRequestDto;
import com.example.springboot.web.dto.Users.UsersResponseDto;
import com.example.springboot.web.dto.Users.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository userRepository;

    public UsersResponseDto findByEmail (String email) {
        Users entity = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email="+ email));

        return new UsersResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, UsersUpdateRequestDto requestDto) {

        Users users = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id="+ id));

        users.updateRegion(requestDto.getRegion());

        return id;
    }
    public UsersResponseDto findById (Long id) {
        Users entity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id="+ id));

        return new UsersResponseDto(entity);
    }
}

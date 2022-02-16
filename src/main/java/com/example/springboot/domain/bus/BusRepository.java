package com.example.springboot.domain.bus;

import com.example.springboot.domain.posts.Posts;
import com.example.springboot.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
    @Query("SELECT p FROM Bus p ORDER BY p.id DESC")
    List<Bus> findAllDesc();

    @Query("SELECT p FROM Bus p ORDER BY p.id ASC")
    List<Bus> findAllAsc();


    @Query("DELETE FROM Bus WHERE region= :strRegion")
    void deleteByRegion(@Param("strRegion") String region);

    Optional<Bus> findByNumber(String number);
    Optional<Bus> findByName(String name);
}

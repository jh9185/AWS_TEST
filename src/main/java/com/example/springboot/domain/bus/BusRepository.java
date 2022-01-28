package com.example.springboot.domain.bus;

import com.example.springboot.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {
    @Query("SELECT p FROM Bus p ORDER BY p.id DESC")
    List<Bus> findAllDesc();

    @Query("DELETE FROM Bus WHERE region= :strRegion")
    void deleteByRegion(@Param("strRegion") String region);
}
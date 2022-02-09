package com.example.springboot.domain.bus;

import com.example.springboot.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusFavoriteRepository extends JpaRepository<BusFavorite, Long> {
     public List<BusFavorite> findByEmail(String email);
}

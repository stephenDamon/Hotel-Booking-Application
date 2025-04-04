package com.airbnb.repository;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    @Query("select r from Reviews r where  r.appUser=:user and r.property=:property")
    Reviews findByUserAndProperty(@Param("user") AppUser user,@Param("property") Property property);
    @Query("select r from Reviews r where r.appUser=:user")
    List<Reviews> findByUser(@Param("user") AppUser user);
}
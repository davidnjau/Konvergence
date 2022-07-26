package com.dave.konvergenz.backend.authentication.repository;

import com.dave.konvergenz.backend.authentication.entity.StaffDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffDetailsRepository extends JpaRepository<StaffDetails, String > {

    StaffDetails findAllByEmailAddress(String email);
    Boolean existsByEmailAddress(String emailAddress);
    Boolean existsByUsername(String userName);
    StaffDetails findAllByUserId(String userId);
}

package com.hellfest22.springboot.repositories;

import com.hellfest22.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<User, String> {

}

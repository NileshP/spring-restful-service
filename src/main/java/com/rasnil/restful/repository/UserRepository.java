package com.rasnil.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rasnil.restful.model.User;
/**
 * 
 * @author Nilesh Parshetti
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

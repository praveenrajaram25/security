package com.spring.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.model.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

	//Users getByUserName(String username);

	Users getByUserName(String username);

}

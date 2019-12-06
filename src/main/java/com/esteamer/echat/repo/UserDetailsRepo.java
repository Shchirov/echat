package com.esteamer.echat.repo;

import com.esteamer.echat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User,String> {
}

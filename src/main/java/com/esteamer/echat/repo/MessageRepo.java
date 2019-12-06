package com.esteamer.echat.repo;

import com.esteamer.echat.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.InterfaceAddress;

public interface MessageRepo extends JpaRepository<Message,Long> {

}

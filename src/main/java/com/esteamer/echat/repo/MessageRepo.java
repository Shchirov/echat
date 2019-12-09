package com.esteamer.echat.repo;

import com.esteamer.echat.domain.Message;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.InterfaceAddress;
import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

    @EntityGraph(attributePaths = "comments")
    List<Message> findAll();
}

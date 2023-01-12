package com.example.newrelic.domain.repository;

import com.example.newrelic.domain.model.Extract;
import com.example.newrelic.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtractRepository extends JpaRepository<Extract, Long> {

    List<Extract> findByUserReceiverId(User user);
}

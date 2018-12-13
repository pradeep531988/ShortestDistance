package com.dsp.shortestdistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dsp.shortestdistance.model.Paths;

@Repository
public interface PathsRepository extends JpaRepository<Paths, Integer> {
    List<Paths> findByFromNode(String from_node);
}

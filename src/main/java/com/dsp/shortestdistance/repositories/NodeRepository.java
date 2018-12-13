package com.dsp.shortestdistance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.shortestdistance.model.Node;

@Repository
public interface NodeRepository extends JpaRepository<Node, Integer> {
	
}

package com.dsp.shortestdistance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsp.shortestdistance.model.Node;
import com.dsp.shortestdistance.repositories.NodeRepository;

@Service
public class CRUDServiceImpl implements ICRUDService{

   @Autowired
	private NodeRepository nodeRepo;
   
	@Override
	public List<Node> getAllNodes() {	
		return nodeRepo.findAll();
	}

}

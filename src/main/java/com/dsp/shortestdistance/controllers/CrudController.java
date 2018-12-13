package com.dsp.shortestdistance.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.shortestdistance.model.Node;
import com.dsp.shortestdistance.model.Paths;
import com.dsp.shortestdistance.repositories.NodeRepository;
import com.dsp.shortestdistance.repositories.PathsRepository;

@RestController
public class CrudController {

	@Autowired
	private NodeRepository nodeRepo;

	@Autowired
	private PathsRepository pathsRepo;

	/*---Add new node---*/
	@PostMapping("/node")
	@Transactional
	public ResponseEntity<?> save(@RequestBody Node node) {
		Node respNode = nodeRepo.save(node);
		return ResponseEntity.ok().body("New Node has been saved with ID:" + respNode.getId());
	}

	/*---Get a node by id---*/
	@GetMapping("/node/{id}")
	public ResponseEntity<Optional<Node>> getNodeById(@PathVariable("id") int id) {
		Optional<Node> node = nodeRepo.findById(id);
		return ResponseEntity.ok().body(node);
	}

	/*---get all nodes---*/
	@GetMapping("/node")
	public ResponseEntity<List<Node>> nodeList() {
		List<Node> nodes = nodeRepo.findAll();
		return ResponseEntity.ok().body(nodes);
	}

	/*---Update a node by id---*/
	@PutMapping("/node/{id}")
	@Transactional
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Node node) {

		Optional<Node> existing = nodeRepo.findById(id);
		if(existing != null && existing.get() != null) {
			node.setId(existing.get().getId());
		}
		nodeRepo.save(node);
		return ResponseEntity.ok().body("Node has been updated successfully.");
	}

	/*---Delete a node by id---*/
	@DeleteMapping("/node/{id}")
	public ResponseEntity<?> deleteNode(@PathVariable("id") int id) {
		nodeRepo.deleteById(id);
		return ResponseEntity.ok().body("Node has been deleted successfully.");
	}


	/*---Add new Paths---*/
	@PostMapping("/paths")
	@Transactional
	public ResponseEntity<?> save(@RequestBody Paths Paths) {
		Paths respPaths = pathsRepo.save(Paths);
		return ResponseEntity.ok().body("New Paths has been saved with ID:" + respPaths.getId());
	}

	/*---Get a Paths by id---*/
	@GetMapping("/paths/{id}")
	public ResponseEntity<Optional<Paths>> get(@PathVariable("id") int id) {
		Optional<Paths> Paths = pathsRepo.findById(id);
		return ResponseEntity.ok().body(Paths);
	}

	/*---get all Pathss---*/
	@GetMapping("/paths")
	public ResponseEntity<List<Paths>> list() {
		List<Paths> Pathss = pathsRepo.findAll();
		return ResponseEntity.ok().body(Pathss);
	}

	/*---Update a Paths by id---*/
	@PutMapping("/paths/{id}")
	@Transactional
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Paths Paths) {

		Optional<Paths> existing = pathsRepo.findById(id);
		if(existing != null && existing.get() != null) {
			Paths.setId(existing.get().getId());
		}
		pathsRepo.save(Paths);
		return ResponseEntity.ok().body("Paths has been updated successfully.");
	}

	/*---Delete a Paths by id---*/
	@DeleteMapping("/paths/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		pathsRepo.deleteById(id);
		return ResponseEntity.ok().body("Paths has been deleted successfully.");
	}

}

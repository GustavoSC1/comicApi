package com.gustavo.api.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavo.api.dto.ComicNewDTO;
import com.gustavo.api.entities.Comic;
import com.gustavo.api.services.ComicService;

@RestController
@RequestMapping(value="/comics")
public class ComicController {
	
	@Autowired
	private ComicService comicService;
	
	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody ComicNewDTO dto) {
		Comic comic = comicService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(comic.getId()).toUri();		
		return ResponseEntity.created(uri).build();
	}
}


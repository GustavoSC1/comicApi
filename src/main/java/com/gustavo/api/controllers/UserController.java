package com.gustavo.api.controllers;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavo.api.dto.ComicDTO;
import com.gustavo.api.dto.AuthorDTO;
import com.gustavo.api.dto.UserDTO;
import com.gustavo.api.dto.UserNewDTO;
import com.gustavo.api.entities.Comic;
import com.gustavo.api.entities.Author;
import com.gustavo.api.entities.User;
import com.gustavo.api.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody UserNewDTO dto) {
		User usuario = userService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuario.getId()).toUri();		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value="{id}")
	public ResponseEntity<UserDTO> find(@PathVariable Integer id) {
		User user = userService.find(id);	
		UserDTO userDto = toUserDTO(user);
		return ResponseEntity.ok().body(userDto);
	}
	
	private UserDTO toUserDTO(User user) {
			UserDTO userDto = new UserDTO(user);
			Set<ComicDTO> comicsDto = new HashSet<>();
			
			for(Comic comic: user.getComics()) {
				ComicDTO comicDto = new ComicDTO(comic);
				Set<AuthorDTO> authors = new HashSet<>();									
				for(Author author: comic.getAuthors()) {
					AuthorDTO authorDto = new AuthorDTO(author);
					authors.add(authorDto);
				}
				comicDto.setAuthors(authors);	
				comicsDto.add(comicDto);
			}
			
			userDto.setComics(comicsDto);
		return userDto;
	}
}


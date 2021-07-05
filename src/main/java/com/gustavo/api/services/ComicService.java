package com.gustavo.api.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import com.gustavo.api.dto.ComicNewDTO;
import com.gustavo.api.entities.Comic;
import com.gustavo.api.entities.Creator;
import com.gustavo.api.repositories.ComicRepository;
import com.gustavo.api.services.utils.CreatorSummary;
import com.gustavo.api.services.utils.MarvelAPIModel;

@Service
public class ComicService {
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CreatorService creatorService;
		
	@Value("${marvel.baseUrl}")
	private String baseUrl;
			
	@Value("${marvel.public_key}")
	private String publicKey;
	
	@Value("${marvel.private_key}")
	private String privateKey;
	
	private WebClient webClient = WebClient.builder().baseUrl(baseUrl)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
		
	public Comic find(Integer id) {
		Optional<Comic> obj = comicRepository.findById(id);
		return obj.orElse(null);
	}
	
	public Comic insert(ComicNewDTO objDto) {
		Comic comic = fromDTO(objDto);		
		comic = comicRepository.save(comic);		
		
		return comic;
	}
	
	public Comic fromDTO(ComicNewDTO objDto) {
		Comic comic = find(objDto.getIdComicMarvel());
		if(comic != null) {
			comic.getUsers().add(userService.find(objDto.getIdUser()));
		} else {
			comic = new Comic();
			MarvelAPIModel marvelModel = getComicByApi(objDto.getIdComicMarvel());
			
			comic.setId(marvelModel.getData().getResults().get(0).getId());
			comic.setTitle(marvelModel.getData().getResults().get(0).getTitle());
			comic.setIsbn(marvelModel.getData().getResults().get(0).getIsbn());
			comic.setDescription(marvelModel.getData().getResults().get(0).getDescription());			
			comic.setPrice(marvelModel.getData().getResults().get(0).getPrices().get(0).getPrice());
			
			comic.getUsers().add(userService.find(objDto.getIdUser()));
			
			for (CreatorSummary creator: marvelModel.getData().getResults().get(0).getCreators().getItems()) {
				Creator obj = creatorService.findByName(creator.getName());				
				if(obj==null) {				
					comic.getCreators().add(new Creator(null, creator.getName()));					
				} else {
					comic.getCreators().add(obj);
				}
			}
			
		}
		return comic;
	}
		
	public MarvelAPIModel getComicByApi(Integer idComicMarvel) {
		String timeStemp = String.valueOf((int)(System.currentTimeMillis() / 1000));
		String hash = getHash(timeStemp, privateKey, publicKey);
		
		Mono<MarvelAPIModel> monoComic = this.webClient
				.method(HttpMethod.GET)
				.uri("/v1/public/comics/{idComicMarvel}?ts={timeStemp}&apikey={publicKey}&hash={hash}", 
						idComicMarvel, timeStemp, publicKey, hash)
				.retrieve()
				.bodyToMono(MarvelAPIModel.class);
		
		MarvelAPIModel comic = monoComic.block();
		
		return comic;
	}
	
	private String getHash(String ts, String privateKey, String publicKey) {
		String value = ts+privateKey+publicKey;
		MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
        return hash.toString(16);
	}
	
}


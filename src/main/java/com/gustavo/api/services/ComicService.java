package com.gustavo.api.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gustavo.api.dto.ComicNewDTO;
import com.gustavo.api.entities.Comic;
import com.gustavo.api.feign.clients.MarvelClient;
import com.gustavo.api.entities.Author;
import com.gustavo.api.repositories.ComicRepository;
import com.gustavo.api.services.utils.CreatorSummary;
import com.gustavo.api.services.utils.MarvelAPIModel;
import com.gustavo.api.services.exceptions.ApiAttributeNullException;

@Service
public class ComicService {
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorService authorService;
					
	@Value("${marvel.public_key}")
	private String publicKey;
	
	@Value("${marvel.private_key}")
	private String privateKey;
		
	@Autowired
	private MarvelClient marvelClient;
		
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
				Author obj = authorService.findByName(creator.getName());				
				if(obj==null) {				
					comic.getAuthors().add(new Author(null, creator.getName()));					
				} else {
					comic.getAuthors().add(obj);
				}
			}
			
			//verificação dos dados
			if(comic.getId().equals(null) || comic.getTitle().equals(null) || comic.getIsbn().equals(null) || 
					comic.getDescription().equals(null) || comic.getPrice().equals(null) || comic.getAuthors().isEmpty()) {
				throw new ApiAttributeNullException("Não é possível cadastrar essa Comic porque ela não possui todos os dados "
						+ "obrigatórios");
			}
			
		}
		return comic;
	}
		
	public MarvelAPIModel getComicByApi(Integer idComicMarvel) {
		String timeStemp = String.valueOf((int)(System.currentTimeMillis() / 1000));
		String hash = getHash(timeStemp);
		
		MarvelAPIModel comic = marvelClient.getComic(idComicMarvel, timeStemp, publicKey, hash);
		
		return comic;
	}
	
	private String getHash(String timeStemp) {
		String value = timeStemp+privateKey+publicKey;
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


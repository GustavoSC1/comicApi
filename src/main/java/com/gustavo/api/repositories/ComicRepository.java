package com.gustavo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavo.api.entities.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Integer> {

}

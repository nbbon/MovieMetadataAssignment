package com.jti.atl.sse.movie.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jti.atl.sse.movie.domain.Movie;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}

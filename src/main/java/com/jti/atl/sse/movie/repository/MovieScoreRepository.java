package com.jti.atl.sse.movie.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jti.atl.sse.movie.domain.MovieScore;

@Repository
@Transactional
public interface MovieScoreRepository extends JpaRepository<MovieScore, Integer> {
	@Query(value = "SELECT ms.* FROM Movie_Scores ms WHERE ms.id = :score_id and ms.movie_id = :movie_id",nativeQuery = true)
	Optional<MovieScore> findMovieScoreById(@Param("movie_id") Integer movie_id, @Param("score_id") Integer score_id);
	
	@Query(value = "SELECT COUNT(ms.id) FROM Movie_Scores ms WHERE ms.movie_id = :movie_id", nativeQuery = true)
	Integer countMovieScore(@Param("movie_id") Integer movie_id);

	@Query(value = "SELECT SUM(ms.score) FROM Movie_Scores ms WHERE ms.movie_id = :movie_id", nativeQuery = true)
	Integer sumMovieScore(@Param("movie_id") Integer movie_id);
}

package com.jti.atl.sse.movie.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jti.atl.sse.exception.ResourceNotFoundException;
import com.jti.atl.sse.movie.domain.Movie;
import com.jti.atl.sse.movie.domain.MovieScore;
import com.jti.atl.sse.movie.repository.MovieRepository;
import com.jti.atl.sse.movie.repository.MovieScoreRepository;

@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	MovieScoreRepository movieScoreRepository;

	public MovieDTO addMovie(MovieDTO movieDto) {
		Movie savedMovie = movieRepository.save(MovieAdapter.convertToMovie(movieDto));
		return MovieAdapter.convertToMovieDTO(savedMovie);
	}

	public MovieDTO getMovie(Integer id) {
		Optional<Movie> result = movieRepository.findById(id);
		if (result.isPresent()) {
			return MovieAdapter.convertToMovieDTO(result.get());
		} else
			return null;
	}

	public List<MovieDTO> getMovies(List<Integer> ids) {
		return movieRepository.findAllById(ids).stream().map(m -> MovieAdapter.convertToMovieDTO(m))
				.collect(Collectors.toList());
	}

	public MovieDTO updateMovie(Integer id, MovieDTO movieDto) {
		return movieRepository.findById(id).map(m -> {
			Movie movie = MovieAdapter.updateMovieFromMovieDTO(m, movieDto);
			movieRepository.save(movie);
			return MovieAdapter.convertToMovieDTO(movie);
		}).orElseThrow(() -> new ResourceNotFoundException("Movie " + id + " not found"));
	}

	public MovieDTO deleteMovie(Integer id) {
		return movieRepository.findById(id).map(m -> {
			m.setDeleted();
			movieRepository.save(m);
			return MovieAdapter.convertToMovieDTO(m);
		}).orElseThrow(() -> new ResourceNotFoundException("Movie " + id + " not found"));
	}

	public MovieScoreDTO addMovieScore(Integer movie_id, MovieScoreDTO movieScoreDto) {
		return movieRepository.findById(movie_id).map(m -> {
			MovieScore movieScore = MovieAdapter.convertToMovieScore(movieScoreDto);
			m.addMovieScore(movieScore);
			m.setAvg_score(calc_Avg_Score(movie_id));
			movieRepository.save(m);
			return MovieAdapter.convertToMovieScoreDTO(movieScore);
		}).orElseThrow(() -> new ResourceNotFoundException("Movie " + movie_id + " not found"));
	}

	public MovieScoreDTO deleteMovieScore(Integer movie_id, Integer score_id) {
		return movieScoreRepository.findMovieScoreById(movie_id, score_id).map(ms -> {
			movieScoreRepository.delete(ms);
			return MovieAdapter.convertToMovieScoreDTO(ms);
		}).orElseThrow(() -> new ResourceNotFoundException("Movie score " + score_id + " not found"));
	}

	private Integer calc_Avg_Score(Integer score_id) {
		Integer numberOfMovieScore = movieScoreRepository.countMovieScore(score_id);
		Integer totalScore = movieScoreRepository.sumMovieScore(score_id);
		return totalScore / numberOfMovieScore;
	}

}

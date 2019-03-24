package com.jti.atl.sse.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jti.atl.sse.movie.service.MovieDTO;
import com.jti.atl.sse.movie.service.MovieScoreDTO;
import com.jti.atl.sse.movie.service.MovieService;

@RestController
public class MovieController {
	@Autowired
	MovieService movieService;

	@GetMapping("/movies/{ids}")
	public ResponseEntity<?> getMovies(@PathVariable List<Integer> ids) {
		if (ids.size() == 1) {
			MovieDTO movieDto = movieService.getMovie(ids.get(0));
			return new ResponseEntity<MovieDTO>(movieDto, HttpStatus.OK);
		} else {
			List<MovieDTO> movies = movieService.getMovies(ids);
			return new ResponseEntity<List<MovieDTO>>(movies, HttpStatus.OK);
		}
	}

	@PostMapping(value = "/movies")
	public ResponseEntity<?> addMovie(@RequestBody @Validated MovieDTO movieDto) {
		MovieDTO newMovieDto = movieService.addMovie(movieDto);
		return new ResponseEntity<MovieDTO>(newMovieDto, HttpStatus.OK);
	}

	@PutMapping(value = "/movies/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody @Validated MovieDTO movieDto) {
		MovieDTO updatedMovieDto = movieService.updateMovie(id, movieDto);
		return new ResponseEntity<MovieDTO>(updatedMovieDto, HttpStatus.OK);
	}

	@DeleteMapping(value = "/movies/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
		MovieDTO deletedMovieDto = movieService.deleteMovie(id);
		return new ResponseEntity<MovieDTO>(deletedMovieDto, HttpStatus.OK);
	}
	
	@PostMapping(value = "/movies/{id}/scores")
	public ResponseEntity<?> addMovieScore(@PathVariable Integer id, @RequestBody @Validated MovieScoreDTO movieScoreDto) {
		MovieScoreDTO newMovieScoreDto = movieService.addMovieScore(id, movieScoreDto);
		return new ResponseEntity<MovieScoreDTO>(newMovieScoreDto, HttpStatus.OK);
	}

	@DeleteMapping(value = "/movies/{id}/scores/{score_id}")
	public ResponseEntity<?> deleteMovieScore(@PathVariable Integer id, @PathVariable Integer score_id) {
		MovieScoreDTO movieScoreDto = movieService.deleteMovieScore(id, score_id);
		return new ResponseEntity<MovieScoreDTO>(movieScoreDto, HttpStatus.OK);
	}

}

package com.jti.atl.sse.movie.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.jti.atl.sse.movie.domain.Genre;
import com.jti.atl.sse.movie.domain.Movie;
import com.jti.atl.sse.movie.domain.MovieScore;

public class MovieAdapter {

	@Autowired
	static MovieService movieService;

	public static Movie convertToMovie(MovieDTO movieDto) {
		if (movieDto == null)
			return null;
		Movie movie = new Movie(movieDto.getId(), movieDto.getName(), movieDto.getPreview_video_url(),
				movieDto.getRuntime(), movieDto.getSynopsis(), movieDto.getCreated_at(), movieDto.getUpdated_at(),
				movieDto.getAvg_score());
		movieDto.getGenre().stream().map(g -> convertToGenre(g)).forEach(g -> movie.addGenre(g));
		movieDto.getMost_recent_scores().stream().map(ms -> convertToMovieScore(ms))
				.forEach(ms -> movie.addMovieScore(ms));
		return movie;
	}

	public static MovieDTO convertToMovieDTO(Movie movie) {
		if (movie == null)
			return null;
		MovieDTO movieDto = new MovieDTO(movie.getId(), movie.getName(), movie.getPreview_video_url(),
				movie.getRuntime(), movie.getSynopsis(), movie.getCreated_at(), movie.getUpdated_at(),
				movie.getAvg_score());
		movie.getGenre().stream().map(g -> convertToGenreDTO(g)).forEach(g -> movieDto.addGenreDTO(g));
		movie.getMost_recent_scores().stream().map(ms -> convertToMovieScoreDTO(ms))
				.forEach(ms -> movieDto.addMovieScoreDTO(ms));
		return movieDto;
	}

	public static List<MovieDTO> convertToListMovieDTO(List<Movie> movies) {
		if (movies == null)
			return null;
		List<MovieDTO> result = movies.stream().map(m -> convertToMovieDTO(m)).collect(Collectors.toList());
		return result;
	}

	public static Genre convertToGenre(GenreDTO genreDto) {
		if (genreDto == null)
			return null;
		Genre genre = new Genre(genreDto.getId(), genreDto.getName());
		return genre;
	}

	public static GenreDTO convertToGenreDTO(Genre genre) {
		if (genre == null)
			return null;
		GenreDTO genreDto = new GenreDTO(genre.getId(), genre.getName());
		return genreDto;
	}

	public static MovieScore convertToMovieScore(MovieScoreDTO movieScoreDto) {
		if (movieScoreDto == null)
			return null;
		MovieScore movieScore = new MovieScore(movieScoreDto.getId(), movieScoreDto.getScore(),
				movieScoreDto.getUser_id(), movieScoreDto.getCreated_at());
		return movieScore;
	}

	public static MovieScoreDTO convertToMovieScoreDTO(MovieScore movieScore) {
		if (movieScore == null)
			return null;
		MovieScoreDTO movieScoreDto = new MovieScoreDTO(movieScore.getId(), movieScore.getScore(),
				movieScore.getUser_id(), movieScore.getCreated_at());
		return movieScoreDto;
	}

	public static Movie updateMovieFromMovieDTO(Movie movie, MovieDTO movieDto) {
		if (movie == null)
			return null;
		if (movieDto == null)
			return movie;
		Movie newMovie = convertToMovie(movieDto);
		newMovie.setId(movie.getId());
		return newMovie;
	}
}

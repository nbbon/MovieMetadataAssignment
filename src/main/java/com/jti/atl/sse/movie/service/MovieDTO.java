package com.jti.atl.sse.movie.service;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {
	private Integer id;
	
	private String name;
	
	private String preview_video_url;
	
	private String runtime;
	private String synopsis;
	
	private List<GenreDTO> genre = new ArrayList<>();
	
	private Long created_at;
	
	private Long updated_at;
	
	private Integer avg_score;
	
	private List<MovieScoreDTO> most_recent_scores = new ArrayList<>();
	
	public MovieDTO() {
		super();
	}
	
	public MovieDTO(Integer id, String name, String preview_video_url, String runtime, String synopsis,
			Long created_at, Long updated_at, Integer avg_score) {
		super();
		this.id = id;
		this.name = name;
		this.preview_video_url = preview_video_url;
		this.runtime = runtime;
		this.synopsis = synopsis;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.avg_score = avg_score;
	}
	
	@Override
	public String toString() {
		return "MovieDTO [id=" + id + ", name=" + name + ", preview_video_url=" + preview_video_url + ", runtime="
				+ runtime + ", synopsis=" + synopsis + ", genre=" + genre + ", created_at=" + created_at + ", updated_at=" + updated_at
				+ ", avg_score=" + avg_score + ", most_recent_scores=" + most_recent_scores + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreview_video_url() {
		return preview_video_url;
	}

	public void setPreview_video_url(String preview_video_url) {
		this.preview_video_url = preview_video_url;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}

	public Long getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Long updated_at) {
		this.updated_at = updated_at;
	}

	public Integer getAvg_score() {
		return avg_score;
	}

	public void setAvg_score(Integer avg_score) {
		this.avg_score = avg_score;
	}

	public List<MovieScoreDTO> getMost_recent_scores() {
		return most_recent_scores;
	}

	public void setMost_recent_scores(List<MovieScoreDTO> most_recent_scores) {
		this.most_recent_scores = most_recent_scores;
	}

	public List<GenreDTO> getGenre() {
		return genre;
	}

	public void setGenre(List<GenreDTO> genre) {
		this.genre = genre;
	}
	
	public void addGenreDTO(GenreDTO genreDto) {
		this.genre.add(genreDto);
	}
	
	public void removeGenreDTO(GenreDTO genreDto) {
		this.genre.remove(genreDto);
	}

	public void addMovieScoreDTO(MovieScoreDTO movieScore) {
		this.most_recent_scores.add(movieScore);
	}
	
	public void removeMovieScoreDTO(MovieScoreDTO movieScore) {
		this.most_recent_scores.remove(movieScore);
	}
	
}

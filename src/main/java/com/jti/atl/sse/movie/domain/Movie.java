package com.jti.atl.sse.movie.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "Movies")
@Where(clause = "DELETED = 0")
public class Movie {
	@Id
	private Integer id;
	
	@NotEmpty
	private String name;
	
	private String preview_video_url;
	
	private String runtime;
	private String synopsis;
	
	private Long created_at;
	
	private Long updated_at;
	
	@Column(name = "DELETED")
    private Integer deleted = 0;
     
	public void setDeleted() {
        this.deleted = 1;
    }
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name="movie_Genre",
			joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = { @JoinColumn(name = "id") }
	)
	List<Genre> genre = new ArrayList<>();
	
	private Integer avg_score;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id")
	private List<MovieScore> most_recent_scores = new ArrayList<>();

	public Movie() {
		super();
	}
	
	public Movie(Integer id, @NotEmpty String name, String preview_video_url, String runtime, String synopsis,
			Long created_at, Long update_at, Integer avg_score) {
		super();
		this.id = id;
		this.name = name;
		this.preview_video_url = preview_video_url;
		this.runtime = runtime;
		this.synopsis = synopsis;
		this.created_at = created_at;
		this.updated_at = update_at;
		this.avg_score = avg_score;
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

	public List<Genre> getGenre() {
		return genre;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}

	public Integer getAvg_score() {
		return avg_score;
	}

	public void setAvg_score(Integer avg_score) {
		this.avg_score = avg_score;
	}

	public List<MovieScore> getMost_recent_scores() {
		return most_recent_scores;
	}

	public void setMost_recent_scores(List<MovieScore> most_recent_scores) {
		this.most_recent_scores = most_recent_scores;
	}
	
	public void addGenre(Genre genre) {
		this.genre.add(genre);
	}

	public void removeGenre(Genre genre) {
		this.genre.remove(genre);
	}

	public void addMovieScore(MovieScore movieScore) {
		this.most_recent_scores.add(movieScore);
	}
	
	public void removeMovieScore(MovieScore movieScore) {
		this.most_recent_scores.remove(movieScore);
	}
	
}

package com.jti.atl.sse.movie.service;

public class MovieScoreDTO {
	
	private Integer id;
	
	private Integer score;
	
	private Integer user_id;
	
	private Long created_at;
		
	@Override
	public String toString() {
		return "MovieScoreDTO [id=" + id + ", score=" + score + ", user_id=" + user_id + ", created_at=" + created_at
				+ "]";
	}

	public MovieScoreDTO() {
		super();
	}

	public MovieScoreDTO(Integer id, Integer score, Integer user_id, Long created_at) {
		super();
		this.id = id;
		this.score = score;
		this.user_id = user_id;
		this.created_at = created_at;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}
	
}

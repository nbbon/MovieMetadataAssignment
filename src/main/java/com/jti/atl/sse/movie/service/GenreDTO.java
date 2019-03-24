package com.jti.atl.sse.movie.service;

public class GenreDTO {
	private Integer id;
	
	private String name;
	
//	private MovieDTO movieDto;
	
	public GenreDTO() {
		super();
	}
	
	public GenreDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "GenreDTO [id=" + id + ", name=" + name + "]";
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

//	public MovieDTO getMovieDto() {
//		return movieDto;
//	}
//
//	public void setMovieDto(MovieDTO movieDto) {
//		this.movieDto = movieDto;
//	}

}

package com.jti.atl.sse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jti.atl.sse.movie.service.GenreDTO;
import com.jti.atl.sse.movie.service.MovieDTO;
import com.jti.atl.sse.movie.service.MovieScoreDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieMetadataAssignmentApplicationTests {
	TestRestTemplate userRestTemplate;
	TestRestTemplate adminRestTemplate;
	TestRestTemplate unauthorizedUserRestTemplate;
	TestRestTemplate unauthorizedAdminRestTemplate;
	String host;
	@LocalServerPort
	int port;

	@Before
	public void setUp() {
		userRestTemplate = new TestRestTemplate("user", "password");
		adminRestTemplate = new TestRestTemplate("admin", "admin");
		unauthorizedUserRestTemplate = new TestRestTemplate("user", "WrongPassword");
		unauthorizedAdminRestTemplate = new TestRestTemplate("admin", "WrongPassword");
		host = "http://localhost:" + port;
	}

	private MovieDTO adminPostMovie(Integer id) throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies");
		MovieDTO movieDto = new MovieDTO(id, "Happy movie", "https://www.youtube.com/xxxx", "1 hr 30 min",
				"Happy movie", 1496174306L, 1496174306L, 0);
		movieDto.addGenreDTO(new GenreDTO(1, "Action"));
		movieDto.addGenreDTO(new GenreDTO(2, "Animated"));

		HttpEntity<MovieDTO> request = new HttpEntity<MovieDTO>(movieDto);
		ResponseEntity<MovieDTO> result = adminRestTemplate.exchange(url.toString(), HttpMethod.POST, request,
				MovieDTO.class);

		if (result.getStatusCode() == HttpStatus.OK) {
			return result.getBody();
		} else {
			return null;
		}
	}

	private boolean adminDeleteMovie(Integer id) throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/" + id);

		ResponseEntity<MovieDTO> result = adminRestTemplate.exchange(url.toString(), HttpMethod.DELETE, null,
				MovieDTO.class);

		if (result.getStatusCode() == HttpStatus.OK) {
			return true;
		} else {
			return false;
		}
	}

//	private MovieScoreDTO userPostMovieScore(Integer movie_id, Integer score_id) throws IllegalStateException, IOException {
//		URL url = new URL(host + "/movies/"+ movie_id + "/scores");
//
//		MovieScoreDTO movieScoreDto = new MovieScoreDTO(score_id, 100, 1, 1496174306L);
//
//		HttpEntity<MovieScoreDTO> request = new HttpEntity<MovieScoreDTO>(movieScoreDto);
//		ResponseEntity<MovieScoreDTO> result = userRestTemplate.exchange(url.toString(), HttpMethod.POST, request,
//				MovieScoreDTO.class);
//		
//		if(result.getStatusCode() == HttpStatus.OK) {
//			return result.getBody();
//		} else {
//			return null;
//		}
//	}
	
//	private boolean userDeleteMovieScore(Integer movie_id, Integer score_id) throws IllegalStateException, IOException {
//		URL url = new URL(host + "/movies/" + movie_id + "/scores/" + score_id);
//
//		ResponseEntity<MovieDTO> result = adminRestTemplate.exchange(url.toString(), HttpMethod.DELETE, null,
//				MovieDTO.class);
//
//		if (result.getStatusCode() == HttpStatus.OK) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	@Test
	public void TC_ADMIN_01_WhenLoggedAdminPOSTMovie_ThenSuccess() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies");

		MovieDTO movieDto = new MovieDTO(1, "Happy movie", "https://www.youtube.com/xxxx", "1 hr 30 min", "Happy movie",
				1496174306L, 1496174306L, 0);
		movieDto.addGenreDTO(new GenreDTO(1, "Action"));
		movieDto.addGenreDTO(new GenreDTO(2, "Animated"));

		HttpEntity<MovieDTO> request = new HttpEntity<MovieDTO>(movieDto);
		ResponseEntity<MovieDTO> result = adminRestTemplate.exchange(url.toString(), HttpMethod.POST, request,
				MovieDTO.class);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertTrue(result.getBody().getId() == 1);
	}

	@Test
	public void TC_ADMIN_02_WhenLoggedAdminPUTMovie_ThenSuccess() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/1");

		assertFalse(adminPostMovie(1) == null);

		MovieDTO movieDto = new MovieDTO(null, "Happy movie Updated", "https://www.youtube.com/xxxx", "1 hr 30 min",
				"Happy movie", 1496174306L, 1496174306L, 0);
		movieDto.addGenreDTO(new GenreDTO(1, "Action"));
		movieDto.addGenreDTO(new GenreDTO(2, "Animated"));

		HttpEntity<MovieDTO> request = new HttpEntity<MovieDTO>(movieDto);
		ResponseEntity<MovieDTO> result = adminRestTemplate.exchange(url.toString(), HttpMethod.PUT, request,
				MovieDTO.class);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertTrue(result.getBody().getId() == 1);
	}

	@Test
	public void TC_ADMIN_03_WhenLoggedAdminDELETEMovie_ThenSuccess() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/1");

		assertFalse(adminPostMovie(1) == null);

		ResponseEntity<MovieDTO> result = adminRestTemplate.exchange(url.toString(), HttpMethod.DELETE, null,
				MovieDTO.class);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertTrue(result.getBody().getId() == 1);
	}

	@Test
	public void TC_ADMIN_04_WhenNotAdminPOSTMovie_ThenFailed() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies");
		MovieDTO movieDto = new MovieDTO(1, "Happy movie", "https://www.youtube.com/xxxx", "1 hr 30 min", "Happy movie",
				1496174306L, 1496174306L, 0);
		movieDto.addGenreDTO(new GenreDTO(1, "Action"));
		movieDto.addGenreDTO(new GenreDTO(2, "Animated"));

		HttpEntity<MovieDTO> request = new HttpEntity<MovieDTO>(movieDto);
		ResponseEntity<MovieDTO> result = userRestTemplate.exchange(url.toString(), HttpMethod.POST, request,
				MovieDTO.class);

		assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());
	}

	@Test
	public void TC_ADMIN_05_WhenNotAdminPUTMovie_ThenFailed() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/1");

		MovieDTO movieDto = new MovieDTO(null, "Happy movie", "https://www.youtube.com/xxxx", "1 hr 30 min",
				"Happy movie", 1496174306L, 1496174306L, 0);
		movieDto.addGenreDTO(new GenreDTO(1, "Action"));
		movieDto.addGenreDTO(new GenreDTO(2, "Animated"));

		HttpEntity<MovieDTO> request = new HttpEntity<MovieDTO>(movieDto);
		ResponseEntity<MovieDTO> result = userRestTemplate.exchange(url.toString(), HttpMethod.PUT, request,
				MovieDTO.class);

		assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());
	}

	@Test
	public void TC_ADMIN_06_WhenNotAdminDELETEMovie_ThenFailed() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/1");

		ResponseEntity<MovieDTO> delete_response = userRestTemplate.exchange(url.toString(), HttpMethod.DELETE, null,
				MovieDTO.class);

		assertEquals(HttpStatus.FORBIDDEN, delete_response.getStatusCode());
	}

	@Test
	public void TC_ADMIN_07_WhenUnauthorizedAdminPOSTMovie_ThenFailed() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies");
		MovieDTO movieDto = new MovieDTO(1, "Happy movie", "https://www.youtube.com/xxxx", "1 hr 30 min", "Happy movie",
				1496174306L, 1496174306L, 0);
		movieDto.addGenreDTO(new GenreDTO(1, "Action"));
		movieDto.addGenreDTO(new GenreDTO(2, "Animated"));

		HttpEntity<MovieDTO> request = new HttpEntity<MovieDTO>(movieDto);
		ResponseEntity<String> result = unauthorizedAdminRestTemplate.exchange(url.toString(), HttpMethod.POST, request,
				String.class);

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
		assertTrue(result.getBody().contains("Unauthorized"));
	}

	@Test
	public void TC_ADMIN_08_WhenUnauthorizedAdminPUTMovie_ThenFailed() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/1");
		MovieDTO movieDto = new MovieDTO(null, "Happy movie", "https://www.youtube.com/xxxx", "1 hr 30 min",
				"Happy movie", 1496174306L, 1496174306L, 0);
		movieDto.addGenreDTO(new GenreDTO(1, "Action"));
		movieDto.addGenreDTO(new GenreDTO(2, "Animated"));

		HttpEntity<MovieDTO> request = new HttpEntity<MovieDTO>(movieDto);
		ResponseEntity<String> result = unauthorizedAdminRestTemplate.exchange(url.toString(), HttpMethod.PUT, request,
				String.class);

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
		assertTrue(result.getBody().contains("Unauthorized"));
	}

	@Test
	public void TC_ADMIN_09_WhenUnauthorizedAdminDELETEMovie_ThenFailed() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/1");

		ResponseEntity<String> result = unauthorizedAdminRestTemplate.exchange(url.toString(), HttpMethod.DELETE, null,
				String.class);

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
		assertTrue(result.getBody().contains("Unauthorized"));
	}

	@Test
	public void TC_USER_00_AddMovieForUserTCs() throws IllegalStateException, IOException {
		assertFalse("FAILED - Add movie id=10", adminPostMovie(10) == null);
	}

	@Test
	public void TC_USER_01_WhenLoggedUserPOSTMovieScore_ThenSuccess() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/10/scores");

		MovieScoreDTO movieScoreDto = new MovieScoreDTO(1, 100, 1, 1496174306L);

		HttpEntity<MovieScoreDTO> request = new HttpEntity<MovieScoreDTO>(movieScoreDto);
		ResponseEntity<MovieScoreDTO> result = userRestTemplate.exchange(url.toString(), HttpMethod.POST, request,
				MovieScoreDTO.class);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertFalse("FAILED - Received id=" + result.getBody().getId() + " - Expected id=1", result.getBody().getId() != 1);
	}

	@Test
	public void TC_USER_02_WhenLoggedUserDELETEMovieScore_ThenSuccess() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/10/scores/1");
		
		ResponseEntity<MovieScoreDTO> result = userRestTemplate.exchange(url.toString(), HttpMethod.DELETE, null,
				MovieScoreDTO.class);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertFalse("FAILED - Received id=" + result.getBody().getId() + " - Expected id=1", result.getBody().getId() != 1);
	}
	
	@Test
	public void TC_USER_03_WhenNotUserPOSTMovieScore_ThenFailed() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/10/scores");

		MovieScoreDTO movieScoreDto = new MovieScoreDTO(1, 100, 1, 1496174306L);

		HttpEntity<MovieScoreDTO> request = new HttpEntity<MovieScoreDTO>(movieScoreDto);
		ResponseEntity<MovieScoreDTO> result = adminRestTemplate.exchange(url.toString(), HttpMethod.POST, request,
				MovieScoreDTO.class);

		assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());
	}

	@Test
	public void TC_USER_04_WhenNotUserDELETEMovieScore_ThenFailed() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/10/scores/1");

		ResponseEntity<MovieScoreDTO> result = adminRestTemplate.exchange(url.toString(), HttpMethod.DELETE, null,
				MovieScoreDTO.class);

		assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());
	}
	
	@Test
	public void TC_USER_05_WhenUnauthorizedUserPOSTMovieScore_ThenFalied() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/10/scores");

		MovieScoreDTO movieScoreDto = new MovieScoreDTO(1, 100, 1, 1496174306L);

		HttpEntity<MovieScoreDTO> request = new HttpEntity<MovieScoreDTO>(movieScoreDto);
		ResponseEntity<MovieScoreDTO> result = unauthorizedUserRestTemplate.exchange(url.toString(), HttpMethod.POST, request,
				MovieScoreDTO.class);

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}

	@Test
	public void TC_USER_06_WhenNotUserDELETEMovieScore_ThenFailed() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/10/scores/1");

		ResponseEntity<MovieScoreDTO> result = unauthorizedUserRestTemplate.exchange(url.toString(), HttpMethod.DELETE, null,
				MovieScoreDTO.class);

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}

	@Test
	public void TC_USER_99_CleanUpAfterUserTCs() throws IllegalStateException, IOException {
		assertFalse("FAILED - Delete movie id=10", adminDeleteMovie(10) == false);
	}
	
	@Test
	public void TC_GEN_00_AddMovieForGeneralTCs() throws IllegalStateException, IOException {
		assertFalse("FAILED - Add movie id=20", adminPostMovie(20) == null);
		assertFalse("FAILED - Add movie id=21", adminPostMovie(21) == null);
		assertFalse("FAILED - Add movie id=22", adminPostMovie(22) == null);
	}

	@Test
	public void TC_GEN_01_WhenLoggedUserGETMovieById_ThenSuccess() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/20");

		ResponseEntity<MovieDTO> result = userRestTemplate.exchange(url.toString(), HttpMethod.GET, null,
				MovieDTO.class);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertFalse("FAILED - Received movie with id=" + result.getBody().getId(), result.getBody().getId() != 20);
	}
	
	@Test
	public void TC_GEN_02_WhenLoggedUserGETMovies_ThenSuccess() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/20,21,22");

		ResponseEntity<List<MovieDTO>> result = userRestTemplate.exchange(url.toString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MovieDTO>>() {});

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertFalse("FAILED - Received " + result.getBody().size() + " movies", result.getBody().size() != 3);
	}
	
	@Test
	public void TC_GEN_03_WhenUnauthorizedUserGETMovieById_ThenFailed() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/20");

		ResponseEntity<Object> result = unauthorizedUserRestTemplate.exchange(url.toString(), HttpMethod.GET, null,
				Object.class);

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}
	
	@Test
	public void TC_GEN_04_WhenUnauthorizedUserGETMovies_ThenFailed() throws IllegalStateException, IOException {
		URL url = new URL(host + "/movies/20,21,22");

		ResponseEntity<Object> result = unauthorizedUserRestTemplate.exchange(url.toString(), HttpMethod.GET, null,
				Object.class);

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}
	
	@Test
	public void TC_GEN_99_CleanUpAfterGeneralTCs() throws IllegalStateException, IOException {
		assertFalse("FAILED - Delete movie id=20", adminDeleteMovie(20) == false);
		assertFalse("FAILED - Delete movie id=21", adminDeleteMovie(21) == false);
		assertFalse("FAILED - Delete movie id=22", adminDeleteMovie(22) == false);
	}
}

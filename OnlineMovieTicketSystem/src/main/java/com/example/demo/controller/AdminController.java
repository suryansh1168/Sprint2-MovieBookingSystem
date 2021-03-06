package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Movie;
import com.example.demo.model.Theater;
import com.example.demo.service.MovieService;
import com.example.demo.service.TheaterService;
/*
 * @author-Suryansh Singh
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private TheaterService theaterService ;
	
	@Autowired
	private MovieService movieService ;
	
	
	private List<Movie> moviesList ;
	
	
	//---------------------------------------Add Theater----------------------------------------------------------------
	
	@RequestMapping(value = "/theater/addTheater", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Theater addTheater(@RequestBody Theater theater) {
		return theaterService.addTheater(theater) ;
	}
	
	//----------------------------------------Delete Theater--------------------------------------------------------------
	
	@RequestMapping(value = "/theater/{theaterId}", method = RequestMethod.DELETE)
	public String deleteTheater(@PathVariable("theaterId") Integer theaterId) {
		return theaterService.deleteTheater(theaterId) ;
	}
	
	//----------------------------------------Add Movie-----------------------------------------------------------------
	
	@RequestMapping(value = "/movies/addMovie/{theaterId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addMovie(@RequestBody Movie movie,@PathVariable("theaterId") int theaterId) {
		Theater theater = theaterService.findTheaterById(theaterId) ;
		moviesList = theater.getMovies() ;
		moviesList.add(movie) ;
		theater.setMovies(moviesList);
		return theaterService.updateTheater(theater) ;
	}
	
	//-----------------------------------------Delete Movie--------------------------------------------------------------
	
	@RequestMapping(value = "/movies/deleteMovie/{movieId}", method = RequestMethod.DELETE)
	public String deleteMovie(@PathVariable("movieId") Integer movieId) {
		return movieService.deleteMovie(movieId) ;
	}
}
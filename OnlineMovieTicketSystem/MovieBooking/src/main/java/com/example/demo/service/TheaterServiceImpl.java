package com.example.demo.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TheaterRepository;
import com.example.demo.exception.EntityAlreadyPresentException;
import com.example.demo.model.Theater;

@Service
@Transactional
public class TheaterServiceImpl implements TheaterService{
	
	@Autowired
	private TheaterRepository dao ;
	
   
	@Override
	public Theater addTheater(Theater theater) {
		Optional<Theater> tId = dao.findById(theater.getTheaterId()) ;
		if(tId.isPresent()) {
			throw new EntityAlreadyPresentException("Already Present") ;
		}
		return dao.save(theater) ;	
	}

	@Override
	public String deleteTheater(int id) {
		Optional<Theater> theater = dao.findById(id) ;
		if(!theater.isPresent() || theater == null) {
			return "No Theater Found By Given Id" ;
		}
		dao.deleteById(id);
		return "Theater Deleted Successfully!" ;
	}

	@Override
	public Theater findTheaterById(int theaterId) {
		Optional<Theater> t = dao.findById(theaterId) ;
		if(!t.isPresent() || t == null) {
			return null ;
		}
		Theater theater = t.get() ;
		return theater ;
	}

	@Override
	public String updateTheater(Theater theater) {
		dao.save(theater) ;
		return "Theater Updated!" ;
	}
   
	
}


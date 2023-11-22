package com.dataproject.btrip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dataproject.btrip.entity.Tour;
import com.dataproject.btrip.repository.TourRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourService {
	
	private final TourRepository tourRepository;
	
	
	// 전체 호출
	public List<Tour> findAll() {
		// TODO Auto-generated method stub
		return tourRepository.findAll();
	}
	
	
	// 카테고리 호출
	public List<Tour> find_cat(int category_num) {
		// TODO Auto-generated method stub
		return tourRepository.findByCategoryNum(category_num);
	}

	public Tour view(int i) {
		// TODO Auto-generated method stub
		return tourRepository.findById(i).get();
	}

}

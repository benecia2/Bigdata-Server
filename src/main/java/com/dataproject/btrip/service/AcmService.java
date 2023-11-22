package com.dataproject.btrip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dataproject.btrip.entity.Accommodation;
import com.dataproject.btrip.repository.AcmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcmService {

	private final AcmRepository accRepository;
	
	
	// 전체 호출
	public List<Accommodation> findAll() {
		// TODO Auto-generated method stub
		return accRepository.findAll();
	}
	
	public List<Accommodation> findByPrice(int acm_num) {
		// TODO Auto-generated method stub
		
		List<Accommodation> list;
		if(acm_num == 1) {
			list = accRepository.findAllByPriceLessThanEqualOrderByScoreDesc(100000);
		}else if(acm_num == 2) {
			list = accRepository.findAllByPriceBetweenOrderByScoreDesc(100000,150000);
		}else if(acm_num == 3) {
			list = accRepository.findAllByPriceBetweenOrderByScoreDesc(150000,200000);
		}else {
			list = accRepository.findAllByPriceGreaterThanEqualOrderByScoreDesc(200000);
		}
			

		
		
		
		return list;
	} 
	
	
	/*
	 * // 카테고리 호출 public List<Accommodation> find_cat(int category_num) { // TODO
	 * Auto-generated method stub return
	 * accRepository.findByCategoryNum(category_num); }
	 */

	public Accommodation view(int i) {
		// TODO Auto-generated method stub
		return accRepository.findById(i).get();
	}
	
}

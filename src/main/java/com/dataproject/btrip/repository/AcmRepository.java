package com.dataproject.btrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataproject.btrip.entity.Accommodation;

public interface AcmRepository extends JpaRepository<Accommodation, Integer> {

	List<Accommodation> findAllByPriceLessThanEqualOrderByScoreDesc(int i);
	
	List<Accommodation> findAllByPriceGreaterThanEqualOrderByScoreDesc(int i);

	List<Accommodation> findAllByPriceBetweenOrderByScoreDesc(int low, int high);


	//List<Accommodation> findByCategoryNum(int category_num);
		
}

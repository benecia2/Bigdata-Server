package com.dataproject.btrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataproject.btrip.entity.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer> {

	List<Tour> findByCategoryNum(int category_num);

}

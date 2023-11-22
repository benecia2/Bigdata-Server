package com.dataproject.btrip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dataproject.btrip.entity.ForiegnVisitors;
import com.dataproject.btrip.entity.SpotVisitors;
import com.dataproject.btrip.entity.TouristSpotVisitors;
import com.dataproject.btrip.repository.ForiegnVisitorsRepository;
import com.dataproject.btrip.repository.SpotVisitorsRepository;
import com.dataproject.btrip.repository.TouristSpotVisitorsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OverviewService {
	private final ForiegnVisitorsRepository foriegnVisitorsRepository;
	private final SpotVisitorsRepository spotVisitorsRepository;
	//private final TouristSpotVisitorsRepository spotVisitorsRepository;
	
	public List<ForiegnVisitors> getForiegnVisitors(){
		return foriegnVisitorsRepository.findAll();
	}
	
	public List<SpotVisitors> getSpotVisitors(){
		return spotVisitorsRepository.findAll();
	}
	
}

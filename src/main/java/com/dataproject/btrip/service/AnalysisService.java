package com.dataproject.btrip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dataproject.btrip.entity.SilhouetteScores;
import com.dataproject.btrip.entity.VariableImportance;
import com.dataproject.btrip.repository.SilhouetteScoresRepository;
import com.dataproject.btrip.repository.VariableImportanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalysisService {
	private final VariableImportanceRepository variableImportanceRepository;
	private final SilhouetteScoresRepository silhouetteScoresRepository;
	
	public List<VariableImportance> getvrblImpList(){
		return variableImportanceRepository.findAllDesc(); 
	}
	
	public List<SilhouetteScores> getShtsScList(){
		return silhouetteScoresRepository.findAll();
	}
}

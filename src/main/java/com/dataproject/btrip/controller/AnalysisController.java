package com.dataproject.btrip.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataproject.btrip.dto.SilhouetteScoresDto;
import com.dataproject.btrip.dto.VariableImportanceDto;
import com.dataproject.btrip.mapper.SilhouettescMapper;
import com.dataproject.btrip.mapper.VrblImpMapper;
import com.dataproject.btrip.service.AnalysisService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/analysis/*")
@RequiredArgsConstructor
public class AnalysisController {
	private final AnalysisService analysisService;
	private final SilhouettescMapper silhouettescMapper;
	private final VrblImpMapper vrblImpMapper;
	
	@GetMapping("vrbl-imp")
	public List<VariableImportanceDto> getvrblImpList(){
		List<VariableImportanceDto> vrblImpList= vrblImpMapper.toDtoList(
													analysisService.getvrblImpList());
		return vrblImpList;
	}
	
	@GetMapping("shts-sc")
	public List<SilhouetteScoresDto> getshtsScList(){
		List<SilhouetteScoresDto> shtsScList = silhouettescMapper.toDtoList(
													analysisService.getShtsScList());
		return shtsScList;
	}
}

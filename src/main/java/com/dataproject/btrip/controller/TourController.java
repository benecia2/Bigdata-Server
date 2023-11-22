package com.dataproject.btrip.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataproject.btrip.dto.AcmDTO;
import com.dataproject.btrip.dto.TourDTO;
import com.dataproject.btrip.entity.Accommodation;
import com.dataproject.btrip.entity.Tour;
import com.dataproject.btrip.service.AcmService;
import com.dataproject.btrip.service.TourService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/tour/*") 
@RequiredArgsConstructor
public class TourController {
	
	private final TourService tourService;
	private final AcmService acmService;
	
	
	// 크롤링 작업
//	@PostConstruct // 애플리케이션 실행 시 함께 실행
//	@Scheduled(cron = "0 0 0 * * *") // 매일 자정 실행
//	public void doCrawling() throws IOException, InterruptedException {
//		
//		System.out.println(">>> <<< 크롤링 작업 시작 >>>");
//        String arg1;
//        ProcessBuilder builder;
//        BufferedReader br;
//
//        // 관광 데이터 작업
//        System.out.println(">>> <<< 관광 정보 크롤링 작업 시작 >>>");
//        arg1 = "src/main/resources/static/python/tour_cr.py";
//        builder = new ProcessBuilder("python",arg1);
//
//        builder.redirectErrorStream(true);
//        Process process = builder.start();
//
//        br = new BufferedReader(new InputStreamReader(process.getInputStream(),"EUC-KR"));
//        String line;
//        while ((line = br.readLine()) != null) {
//            System.out.println(">>>  " + line);
//        }
//        
//        // 프로세스 종료 대기
//        int exitval = process.waitFor();
//        
//        if(exitval !=0){
//            //비정상종료
//            System.out.println("관광 크롤링 비정상종료");
//        }
//        
//        
//        // 숙박 데이터 작업
//        System.out.println(">>> <<< 숙박 정보 크롤링 작업 시작 >>>");
//        arg1 = "src/main/resources/static/python/acm_cr.py";
//        builder = new ProcessBuilder("python",arg1);
//
//        builder.redirectErrorStream(true);
//        process = builder.start();
//
//        br = new BufferedReader(new InputStreamReader(process.getInputStream(),"EUC-KR"));
//        while ((line = br.readLine()) != null) {
//            System.out.println(">>>  " + line);
//        }
//        
//        // 프로세스가 종료 대기
//        exitval = process.waitFor();
//        
//        if(exitval !=0){
//            //비정상종료
//            System.out.println("숙박 크롤링 비정상종료");
//        }
//        
//
//    }
	
	// 관광 카테고리 호출
	// 카테고리 0 : 전체
	// 카테고리 1 : 미식 + 2.자연
	// 카테고리 2 : 자연 + 4.역사
	// 카테고리 3 : 쇼핑
	// 카테고리 4 : 역사 + 5.문화 + 1.미식
	// 카테고리 5 : 문화
	
	// 숙박 가격대 호출 (평점순)
	// 비용 0 : 전체
	// 비용 1 : 100$ 이하
	// 비용 2 : 100$ ~ 200$
	// 비용 3 : 200$ ~ 300$
	// 비용 4 : 300$ 이상
	@GetMapping("/list/{tour_num}/{acm_num}")
	public Map<String, Object> list_tour(@PathVariable int tour_num,
										 @PathVariable int acm_num) {
		
		List<Tour> tourListDAO_1 = Collections.emptyList();
		List<Tour> tourListDAO_2 = Collections.emptyList();
		List<Accommodation> acmListDAO = Collections.emptyList();
		
		
		// 관광 데이터 호출
		if(tour_num == 1) { //미식 + 자연 + 100~200
			tourListDAO_1 = tourService.find_cat(1);		
			tourListDAO_2 = tourService.find_cat(2);
		}			
		else if(tour_num == 2) { // 자연 + 역사 + 300+
			tourListDAO_1 = tourService.find_cat(2);		
			tourListDAO_2 = tourService.find_cat(4);
		}
		else if(tour_num == 3) { // 쇼핑 + 200~300
			tourListDAO_1 = tourService.find_cat(3);		
			tourListDAO_2 = tourService.find_cat(3);
		}
		else if(tour_num == 4) { // 역사 + 문화 + 미식 + 200~300	 
			tourListDAO_1 = tourService.find_cat(4);		
			tourListDAO_1.addAll(tourService.find_cat(5));
			tourListDAO_2 = tourService.find_cat(1);
		}
		if(tour_num == 5) { // 미식 + 역사 + 300+
			tourListDAO_1 = tourService.find_cat(1);		
			tourListDAO_2 = tourService.find_cat(4);
		}
									
		List<TourDTO> rec_1 = tourListDAO_1.stream()
				.map(Tour -> new TourDTO(Tour))
				.collect(Collectors.toList());
		
		List<TourDTO> rec_2 = tourListDAO_2.stream()
				.map(Tour -> new TourDTO(Tour))
				.collect(Collectors.toList());
		
		System.out.println("[ 관광 호출 ]");
		System.out.println(rec_1.get(0).getCategory() + " 목록 : " + rec_1.size() + " items");
		System.out.println(rec_2.get(0).getCategory() + " 목록 : " + rec_2.size() + " items");
		
		
		// 숙박 데이터 호출
		acmListDAO = acmService.findByPrice(acm_num);
		List<AcmDTO> acmList = acmListDAO.stream()
				.map(Acm -> new AcmDTO(Acm))
				.collect(Collectors.toList());
		
		System.out.println("[ 숙박시설 호출 ]");
		System.out.println("전체 목록 : " + acmList.size() + " items");
		
		Map<String, Object> tourList = new HashMap<>();
		tourList.put("rec_1", rec_1);
		tourList.put("rec_2", rec_2);
		tourList.put("acm", acmList);
		
		return tourList;
				
	}
	
	
	// 테스트 호출
	@GetMapping("/test")
	public List<TourDTO> list_test(){
		
		int category_num = 0;
		
		String category = "전체";
		List<Tour> tourListDAO = Collections.emptyList();
		
		if(category_num == 0) {
			tourListDAO = tourService.findAll();
			
		}
		else if(category_num != 0) {
			tourListDAO = tourService.find_cat(category_num);
			category = tourListDAO.get(0).getCategory();
		}
		
							
		List<TourDTO> tourList = tourListDAO.stream()
				.map(Tour -> new TourDTO(Tour))
				.collect(Collectors.toList());
		

		System.out.println("[ Test 호출 ]");
		System.out.println("[ 관광 호출 ]");
		System.out.println(category + " 목록 : " + tourList.size() + " items");		 			
		return tourList;	
	}
	
	//
}

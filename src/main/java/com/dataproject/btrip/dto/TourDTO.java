package com.dataproject.btrip.dto;


import com.dataproject.btrip.entity.Tour;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourDTO {
	
	private int id;
    private String title;
    private String link_url;
    private	String img_url;
    private String views;
    private String comments;
    private String likes;
    private String category;
    private int categoryNum;
    
    
    
    // DAO -> DTO 변환
    public TourDTO(Tour tourDAO) {
    	
    	id = tourDAO.getId();
    	title = tourDAO.getTitle();
    	link_url = tourDAO.getLink_url();
    	img_url = tourDAO.getImg_url();
    	views = tourDAO.getViews();
    	comments = tourDAO.getComments();
    	likes = tourDAO.getLikes();
    	category = tourDAO.getCategory();
    	categoryNum = tourDAO.getCategoryNum();
    	 
    }
    
}

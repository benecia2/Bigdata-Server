package com.dataproject.btrip.dto;


import com.dataproject.btrip.entity.Accommodation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcmDTO {
	
	private int id;
    private String title;
    private	String location;
    private String img_url;
    private String link_url;
    private int rating;
    private String score;
    private int price;
    private String tax_charge;
    
    
    
    // DAO -> DTO 변환
    public AcmDTO(Accommodation acmDAO) {
    	
    	id = acmDAO.getId();
    	title = acmDAO.getTitle();
    	location = acmDAO.getLocation();
    	img_url = acmDAO.getImg_url();
    	link_url = acmDAO.getLink_url();
    	rating = acmDAO.getRating();
    	score = acmDAO.getScore();
    	price = acmDAO.getPrice();
    	tax_charge = acmDAO.getTax_charge();
    	 
    }
    
}

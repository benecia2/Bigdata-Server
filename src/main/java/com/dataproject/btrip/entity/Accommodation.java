package com.dataproject.btrip.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "acm_list")
@AllArgsConstructor
@NoArgsConstructor
public class Accommodation {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String title;
    private	String location;
    private String img_url;
    private String link_url;
    private int rating;
    private String score;
    private int price;
    private String tax_charge;
    
}

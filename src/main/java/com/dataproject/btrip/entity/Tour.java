package com.dataproject.btrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "tour_list")
@AllArgsConstructor
@NoArgsConstructor
public class Tour {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String title;
    private String link_url;
    private	String img_url;
    private String views;
    private String comments;
    private String likes;
    private String category;
    @Column(name= "category_num")
    private int categoryNum;
	
}

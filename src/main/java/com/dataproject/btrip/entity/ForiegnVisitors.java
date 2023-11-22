package com.dataproject.btrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "foreign_visitor")
public class ForiegnVisitors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "year", nullable = false)
	private String year;
	@Column(name = "month", nullable = false)
	private String month;
	@Column(name = "visitor", nullable = false)
	private int visitor;
	@Column(name = "excepted", nullable = false)
	private int excepted;
}

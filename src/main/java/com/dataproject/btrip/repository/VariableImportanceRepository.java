package com.dataproject.btrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dataproject.btrip.entity.VariableImportance;

public interface VariableImportanceRepository extends JpaRepository<VariableImportance, Long> {
	@Query("select v from VariableImportance v order by v.importance desc")
	List<VariableImportance> findAllDesc();
}

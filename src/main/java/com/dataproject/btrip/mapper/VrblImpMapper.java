package com.dataproject.btrip.mapper;

import org.mapstruct.Mapper;

import com.dataproject.btrip.dto.VariableImportanceDto;
import com.dataproject.btrip.entity.VariableImportance;

@Mapper(componentModel = "spring")
public interface VrblImpMapper extends GenericMapper<VariableImportanceDto, VariableImportance> {

}

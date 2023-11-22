package com.dataproject.btrip.mapper;

import org.mapstruct.Mapper;

import com.dataproject.btrip.dto.ForiegnVisitorsDto;
import com.dataproject.btrip.entity.ForiegnVisitors;

@Mapper(componentModel = "spring")
public interface ForiegnVisitorsMapper extends GenericMapper<ForiegnVisitorsDto, ForiegnVisitors> {
	
}

package com.dataproject.btrip.mapper;

import org.mapstruct.Mapper;

import com.dataproject.btrip.dto.SpotVisitorsDto;
import com.dataproject.btrip.entity.SpotVisitors;

@Mapper(componentModel = "spring")
public interface SpotVisitorsMapper extends GenericMapper<SpotVisitorsDto, SpotVisitors> {

}

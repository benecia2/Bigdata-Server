package com.dataproject.btrip.mapper;

import org.mapstruct.Mapper;

import com.dataproject.btrip.dto.TouristSpotVisitorsDto;
import com.dataproject.btrip.entity.TouristSpotVisitors;

@Mapper(componentModel = "spring")
public interface TouristSpotVisitorsMapper extends GenericMapper<TouristSpotVisitorsDto, TouristSpotVisitors> {

}

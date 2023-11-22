package com.dataproject.btrip.mapper;

import org.mapstruct.Mapper;

import com.dataproject.btrip.dto.SilhouetteScoresDto;
import com.dataproject.btrip.entity.SilhouetteScores;

@Mapper(componentModel = "spring")
public interface SilhouettescMapper extends GenericMapper<SilhouetteScoresDto, SilhouetteScores> {

}

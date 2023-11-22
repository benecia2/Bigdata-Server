package com.dataproject.btrip.mapper;

import java.util.List;

import org.mapstruct.MappingTarget;

public interface GenericMapper<D,E> {
	D toDto(E e);
	List<D> toDtoList(List<E> e);
}

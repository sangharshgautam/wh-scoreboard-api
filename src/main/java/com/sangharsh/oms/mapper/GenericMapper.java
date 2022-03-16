package com.sangharsh.oms.mapper;

import com.sangharsh.oms.model.BaseModel;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface GenericMapper<T extends BaseModel, U> {
//    @Mapping(target = "team", ignore = true)
    T toModel(U dto);
    @Mapping(source = "id", target = "id")
    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
    U toDto(T model);
    List<U> map(List<T> models);

    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
    T fromTwoOther(U dto, @MappingTarget T entity);
}

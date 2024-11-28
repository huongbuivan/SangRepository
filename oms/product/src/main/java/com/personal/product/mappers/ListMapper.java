package com.personal.product.mappers;

import java.util.List;

public interface ListMapper<Entity, DTO> {
    List<DTO> toDTOList(List<Entity> entities);
}

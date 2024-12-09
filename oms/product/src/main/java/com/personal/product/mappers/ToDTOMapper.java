package com.personal.product.mappers;

public interface ToDTOMapper<Entity, DTO> {
    DTO toDTO(Entity entity);
}

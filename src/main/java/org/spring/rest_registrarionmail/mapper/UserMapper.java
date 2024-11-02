package org.spring.rest_registrarionmail.mapper;

import org.mapstruct.Mapper;

import org.spring.rest_registrarionmail.model.DTO.UserRegistrationDTO;
import org.spring.rest_registrarionmail.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRegistrationDTO userRegistrationDTO);
}

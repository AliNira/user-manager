package org.nira.usermanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.nira.usermanager.dto.UserRequestDto;
import org.nira.usermanager.dto.UserResponseDto;
import org.nira.usermanager.entity.User;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserResponseDto mapToUserResponseDto(User user);

    User mapToUser(UserRequestDto userRequestDto);
}

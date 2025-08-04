package org.nira.usermanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.nira.usermanager.dto.UserDto;
import org.nira.usermanager.entity.User;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}

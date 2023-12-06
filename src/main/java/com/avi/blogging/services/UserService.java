package com.avi.blogging.services;

import com.avi.blogging.Payload.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
          UserDto createUser(UserDto userDto);
          UserDto updateUser(UserDto userDto,Long userId);
          UserDto getUserById(Long userId );
          List<UserDto> getAllUser();
          void deleteUser(Long userId);
}

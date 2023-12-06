package com.avi.blogging.Sevices.Serviceimpl;

import com.avi.blogging.Entity.User;
import com.avi.blogging.Exceptation.ResourceNotFoundException;
import com.avi.blogging.Payload.UserDto;
import com.avi.blogging.Repository.UserRepository;
import com.avi.blogging.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user =this.dtoToUser(userDto);
       User savedUser= userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user=this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User user1=userRepository.save(user);
        UserDto userDto1=this.userToDto(user1);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Long userId) {
     User user=this.userRepository.findById(userId)
             .orElseThrow(()->new ResourceNotFoundException("user","id",userId));
     UserDto userDto=this.userToDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> user=this.userRepository.findAll();
        List<UserDto> userDtos=user.stream().map(user1 -> this.userToDto(user1)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Long userId) {
     User user=this.userRepository.findById(userId)
             .orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
     this.userRepository.delete(user);
    }
    public User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
//        user.setUserId(userDto.getUserId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
        return user;

    }
    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);

        return userDto;
    }
}

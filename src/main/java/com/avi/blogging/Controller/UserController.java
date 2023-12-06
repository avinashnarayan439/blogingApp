package com.avi.blogging.Controller;
import com.avi.blogging.Payload.UserDto;
import com.avi.blogging.services.UserService;
import com.avi.blogging.apiResponse.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody@Valid UserDto userDto){
        UserDto createUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto,@PathVariable Long userId){
         UserDto updatedUser=this.userService.updateUser(userDto,userId);
         return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{userId}")

    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        this.userService.deleteUser(userId);
        ApiResponse apiResponse= new ApiResponse("user deleted successfully",true);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(this.userService.getAllUser());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Long userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}

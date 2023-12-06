package com.avi.blogging.Controller;

import com.avi.blogging.Entity.Post;
import com.avi.blogging.Payload.PostDto;
import com.avi.blogging.Payload.UserDto;
import com.avi.blogging.PostResponse;
import com.avi.blogging.apiResponse.ApiResponse;
import com.avi.blogging.services.PostService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
   private ModelMapper modelMapper;
    //create post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody@Valid PostDto postDto,
                                              @PathVariable Long userId, @PathVariable Long categoryId){
        PostDto createPost=this.postService.createpost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
    }
    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable Long userId ,@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                                       @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize){
        PostResponse post=this.postService.getPostByUser(userId ,pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(post,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Long categoryId
            ,@RequestParam (value = "pageNumber",defaultValue = "1",required = false)int pageNumber
            ,@RequestParam (value = "pageSize",defaultValue = "5",required = false)int pageSize){
        PostResponse category=this.postService.getPostByCategory(categoryId,pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(category,HttpStatus.OK);
    }
    //get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                                         @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize){
        PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }
    //delete post
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId){
        this.postService.deletePost(postId);
        ApiResponse apiResponse= new ApiResponse("post deleted successfully",true);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
    }
    // get post by ID
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable Long postId){
        PostDto postDto=this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Long postId){
        PostDto postDto1=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);

    }



}

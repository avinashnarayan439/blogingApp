package com.avi.blogging.Sevices.Serviceimpl;
import com.avi.blogging.Entity.Post;
import com.avi.blogging.Entity.Category;
import com.avi.blogging.Exceptation.ResourceNotFoundException;
import com.avi.blogging.Payload.PostDto;
import com.avi.blogging.PostResponse;
import com.avi.blogging.Repository.CategoryRepository;
import com.avi.blogging.Repository.PostRepository;
import com.avi.blogging.Repository.UserRepository;
import com.avi.blogging.services.PostService;
import com.avi.blogging.Entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public PostDto createpost(PostDto postDto,Long userId,Long categoryId) {
        User user=this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("userId","user",userId));
        Category category=this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));
        //System.out.println("this is my category id  :"+category.getCategoryId());
      //  System.out.println("this is my userId  :"+user.getUserId());
        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createdPost=postRepository.save(post);
        //userRepository.save(post.getUser());
       // categoryRepository.save(post.getCategory());
        System.out.println(createdPost.getUser().getUserId()+"  "+createdPost.getCategory().getCategoryId());
        return this.modelMapper.map(createdPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post=this.postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("postId","post",postId));
        post.setPostTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddDate(postDto.getAddedDate());
        Post updatedPost=this.postRepository.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post=this.postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
        this.postRepository.delete(post);

    }

    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize) {
        Pageable p=PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost =this.postRepository.findAll(p);
        List<Post> allPost=pagePost.getContent();
        List<PostDto> postList=new ArrayList<>();
        for(Post printPost:allPost){
            postList.add(this.modelMapper.map(printPost,PostDto.class));
        }
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Long postId) {
       Post post= this.postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getPostByCategory(Long categoryId,int pageNumber, int pageSize) {
        System.out.println("categoryId  :"+categoryId+" pageNumber  :"+pageNumber+"  pageSize  :"+pageSize);
        Category cat=this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("categoryId","category",categoryId));
        Pageable p=PageRequest.of(pageNumber,pageSize);
        Page<Post> posts=this.postRepository.findByCategory(cat,p);
        List<Post> postList=posts.getContent();
        List<PostDto> postDtos=new ArrayList<>();
        for(Post print:postList){
            postDtos.add(this.modelMapper.map(print,PostDto.class));
        }
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setLastPage(posts.isLast());
        postResponse.setTotalPages(posts.getTotalPages());
        return postResponse;
    }

    @Override
    public PostResponse getPostByUser(Long userId ,int pageNumber,int pageSize) {
        User user=this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user","userId",userId));
        Pageable p=PageRequest.of( pageNumber,pageSize);
        Page<Post> posts=this.postRepository.findByUser(user,p);
        List<PostDto> postDtos=new ArrayList<>();
        for (Post print:posts){
            postDtos.add(this.modelMapper.map(print,PostDto.class));
        }
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setLastPage(posts.isLast());
        postResponse.setTotalPages(posts.getTotalPages());
        return postResponse;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }
}

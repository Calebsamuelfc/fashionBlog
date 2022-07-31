package com.kay.fashion_blog.service.serviceImplementation;

import com.kay.fashion_blog.constants.DefaultMessage;
import com.kay.fashion_blog.constants.Roles;
import com.kay.fashion_blog.dto.CommentResponsePayload;
import com.kay.fashion_blog.dto.PostRequestPayload;
import com.kay.fashion_blog.dto.PostResponsePayload;
import com.kay.fashion_blog.dto.SearchDto;
import com.kay.fashion_blog.entity.*;
import com.kay.fashion_blog.exceptions.NotAuthorizedException;
import com.kay.fashion_blog.exceptions.ResourceNotFoundException;
import com.kay.fashion_blog.repositories.*;
import com.kay.fashion_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final HttpSession httpSession;
    private final PostsRepository postRepository;
    private final CommentsRepository commentRepository;
    private final UsersRepository userRepository;
    private final LikesRepository likesRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public String createPost(PostRequestPayload postRequestPayload, Long catId) throws NotAuthorizedException, ResourceNotFoundException {
        Roles role = (Roles) httpSession.getAttribute("Role");//TODO
        if (!role.equals(Roles.ADMIN)){
            throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);
        }
        Categories categories = categoryRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND));

        Posts post = Posts.builder()
                .body(postRequestPayload.getContent())
                .title(postRequestPayload.getTitle())
                .categories(categories)
                .users((Users) httpSession.getAttribute("user"))
                .build();

        postRepository.save(post);
        return DefaultMessage.SUCCESSFUL_POST_CREATION;
    }

    @Override
    public PostResponsePayload retrievePost(Long postId) throws ResourceNotFoundException {
        Posts posts =  postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND));
        return postResponseBuilder(posts);
    }

    @Override
    public String editPost(PostRequestPayload postRequestPayload, Long postId) throws ResourceNotFoundException, NotAuthorizedException {
        Roles role = (Roles) httpSession.getAttribute("Role");//TODO
        if (!role.equals(Roles.ADMIN)){
            throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);
        }
        Optional<Posts> postCheck = postRepository.findById(postId);
        if (postCheck.isPresent()){
            Posts editedPost = postCheck.get();
            editedPost.setTitle(postRequestPayload.getTitle());
            editedPost.setBody(postRequestPayload.getContent());
            editedPost.setTimeUpdated(LocalDateTime.now());
            postRepository.save(editedPost);
            return DefaultMessage.SUCCESSFUL_POST_UPDATE;
        }
        throw new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND);
    }

    @Override
    public String deletePost(Long id) throws NotAuthorizedException {
        Roles role = (Roles) httpSession.getAttribute("Role");
        if (!role.equals(Roles.ADMIN)){
            throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);
        }
        postRepository.deleteById(id);
        return DefaultMessage.SUCCESSFUL_POST_DELETION;
    }

    @Override
    public List<PostResponsePayload> viewAllPosts() {
        List<Posts> postList = postRepository.findAll();
        List<PostResponsePayload> returnList = new ArrayList<>();
        for (Posts posts: postList) {
            returnList.add(postResponseBuilder(posts));
        }
        return returnList;
    }
    @Override
    public List<PostResponsePayload> searchPostByTitle(SearchDto searchDto) {
        List<Posts> postList = postRepository.findPostsByTitleContainsIgnoreCase(searchDto.getText());
        List<PostResponsePayload> returnList = new ArrayList<>();
        for (Posts posts: postList) {
            returnList.add(postResponseBuilder(posts));
        }
        return returnList;
    }

    private List<CommentResponsePayload> returnListOfCommentResponsePayload(Posts posts){
        List<CommentResponsePayload> returnComments = new ArrayList<>();
        for (Comments comments: posts.getComments()) {
            CommentResponsePayload crp = CommentResponsePayload.builder()
                    .customerName(comments.getUsers().getName())
                    .content(comments.getContent())
                    .timeCreated(comments.getTimeCreated())
                    .timeUpdated(comments.getTimeUpdated())
                    .build();
            returnComments.add(crp);
        }
        return returnComments;
    }
    private PostResponsePayload postResponseBuilder (Posts posts){
        return PostResponsePayload.builder()
                .title(posts.getTitle())
                .content(posts.getBody())
                .categories(posts.getCategories().getText())
                .author(posts.getUsers().getName())
                .commentsList(returnListOfCommentResponsePayload(posts))
                .numberOfLikes(posts.getLikes().size())
                .likedBy(returnListOfUsersThatLikedPost(posts))
                .build();
    }
    private List<String> returnListOfUsersThatLikedPost (Posts posts){
        List<String> str = new ArrayList<>();
        for(Likes likes : posts.getLikes()){
            str.add(likes.getUsers().getName());
        }
        return str;
    }
}

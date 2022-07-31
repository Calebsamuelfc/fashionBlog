package com.kay.fashion_blog.service.serviceImplementation;

import com.kay.fashion_blog.constants.DefaultMessage;
import com.kay.fashion_blog.constants.Roles;
import com.kay.fashion_blog.dto.CategoryDto;
import com.kay.fashion_blog.dto.CommentDto;
import com.kay.fashion_blog.entity.*;
import com.kay.fashion_blog.exceptions.NotAuthorizedException;
import com.kay.fashion_blog.exceptions.ResourceNotFoundException;
import com.kay.fashion_blog.repositories.*;
import com.kay.fashion_blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class
UserServiceImpl implements UserService {

    private final HttpSession httpSession;
    private final PostsRepository postRepository;
    private final CommentsRepository commentRepository;
    private final UsersRepository userRepository;
    private final LikesRepository likesRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Users findUserById(Long id) {
        return null;
    }


    @Override
    public String deleteComment(Long id) throws NotAuthorizedException {
        Roles role = (Roles) httpSession.getAttribute("Role");
        if (!role.equals(Roles.ADMIN)){
            throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);
        }
        commentRepository.deleteById(id);
        return DefaultMessage.SUCCESSFUL_COMMENT_DELETION;
    }

    @Override
    public Comments getComment(Long id) throws ResourceNotFoundException {
        Optional<Comments> comment = commentRepository.findById(id);
        if(comment.isPresent())
            return comment.get();
        else throw new ResourceNotFoundException(DefaultMessage.COMMENT_NOT_FOUND);
    }

    @Override
    public String editComment(CommentDto commentDto, Long commentId) throws NotAuthorizedException, ResourceNotFoundException {
        Optional<Users> user = userRepository.findById((Long) httpSession.getAttribute("Login_ID"));
        Optional<Comments> comment = commentRepository.findById(commentId);
        if (user.isPresent()){
            if (comment.isPresent()){
                if (user.get().equals(comment.get().getUsers())){
                    Comments editedComment = comment.get();
                    editedComment.setContent(commentDto.getContent());
                    return DefaultMessage.SUCCESSFUL_COMMENT_UPDATE;
                }else throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);
            }else throw new ResourceNotFoundException(DefaultMessage.COMMENT_NOT_FOUND);

        }else throw new NotAuthorizedException(DefaultMessage.NOT_AUTHORIZED_ERROR);

    }

    @Override
    public String createComment(CommentDto commentDto, Long postId) throws ResourceNotFoundException {
        Optional<Posts> post = postRepository.findById(postId);
        Optional<Users> user = userRepository.findById((Long) httpSession.getAttribute("user_id"));
        if (user.isPresent()){
            if(post.isPresent()){
                Posts posts = post.get();
                List<Comments> commentsList = posts.getComments();
                Comments comment = new Comments();
                comment.setContent(commentDto.getContent());
                comment.setUsers(user.get());
                commentRepository.save(comment);
                commentsList.add(comment);
                posts.setComments(commentsList);
                postRepository.save(posts);
            }else{
                throw new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND);
            }
        }
//        else{
//            Users visitor = Users.builder()
//                    .name("Anonymous")
//                    .roles(Roles.VISITOR)
//                    .build();
//            if(post.isPresent()){
//                Posts posts = post.get();
//                List<Comments> commentsList = posts.getComments();
//                Comments comment = new Comments();
//                comment.setContent(commentDto.getContent());
////                comment.setUsers(visitor);
//                commentRepository.save(comment);
//                commentsList.add(comment);
//                posts.setComments(commentsList);
//                postRepository.save(posts);
//            }else{
//                throw new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND);
//            }
//        }
        return DefaultMessage.SUCCESSFUL_COMMENT_CREATION;
    }

    @Override
    public String likePost(Long postId) throws ResourceNotFoundException{
        Posts post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND));
        Optional<Users> user = userRepository.findById((Long) httpSession.getAttribute("user_id"));
        if (user.isPresent()){
            List<Likes> likesList = post.getLikes();
            Likes likes = new Likes();
            likes.setUsers(user.get());
            likesRepository.save(likes);
            likesList.add(likes);
            post.setLikes(likesList);
            postRepository.save(post);
        }
        return DefaultMessage.POST_SUCCESSFULLY_LIKED;
    }

    @Override
    public List<Comments> viewAllComments(Long postId) throws ResourceNotFoundException {
        return null;
    }

//    @Override
//    public List<Comments> viewAllComments(Long postId) throws ResourceNotFoundException {
//        Posts posts = postRepository.findById(postId)
//                .orElseThrow(() -> new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND));
//        return commentRepository.findAllByPosts(posts);
//    }

    @Override
    public String createCategory(CategoryDto categoryDto) {
        Categories categories = Categories.builder()
                .text(categoryDto.getCategoryName())
                .build();
        categoryRepository.save(categories);
        return DefaultMessage.SUCCESSFUL_CATEGORY_CREATION;
    }

    @Override
    public Integer getNumberOfLikes(Long postId) throws ResourceNotFoundException {
        Posts post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(DefaultMessage.POST_NOT_FOUND));
        return post.getLikes().size();
    }

}

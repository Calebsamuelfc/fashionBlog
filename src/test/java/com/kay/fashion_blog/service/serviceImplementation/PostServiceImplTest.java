package com.kay.fashion_blog.service.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kay.fashion_blog.constants.Roles;
import com.kay.fashion_blog.dto.CommentResponsePayload;
import com.kay.fashion_blog.dto.PostRequestPayload;
import com.kay.fashion_blog.dto.PostResponsePayload;
import com.kay.fashion_blog.dto.SearchDto;
import com.kay.fashion_blog.entity.Categories;
import com.kay.fashion_blog.entity.Comments;
import com.kay.fashion_blog.entity.Likes;
import com.kay.fashion_blog.entity.Posts;
import com.kay.fashion_blog.entity.Users;
import com.kay.fashion_blog.exceptions.NotAuthorizedException;
import com.kay.fashion_blog.exceptions.ResourceNotFoundException;
import com.kay.fashion_blog.repositories.CategoryRepository;
import com.kay.fashion_blog.repositories.CommentsRepository;
import com.kay.fashion_blog.repositories.LikesRepository;
import com.kay.fashion_blog.repositories.PostsRepository;
import com.kay.fashion_blog.repositories.UsersRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PostServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PostServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private CommentsRepository commentsRepository;

    @MockBean
    private HttpSession httpSession;

    @MockBean
    private LikesRepository likesRepository;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @MockBean
    private PostsRepository postsRepository;

    @MockBean
    private UsersRepository usersRepository;

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostRequestPayload, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreatePost() throws NotAuthorizedException, ResourceNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.kay.fashion_blog.constants.Roles.equals(Object)" because "role" is null
        //       at com.kay.fashion_blog.service.serviceImplementation.PostServiceImpl.createPost(PostServiceImpl.java:37)
        //   In order to prevent createPost(PostRequestPayload, Long)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createPost(PostRequestPayload, Long).
        //   See https://diff.blue/R013 to resolve this issue.

        PostServiceImpl postServiceImpl = new PostServiceImpl(new MockHttpSession(), mock(PostsRepository.class),
                mock(CommentsRepository.class), mock(UsersRepository.class), mock(LikesRepository.class),
                mock(CategoryRepository.class));
        postServiceImpl.createPost(new PostRequestPayload("Dr", "Not all who wander are lost"), 123L);
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostRequestPayload, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreatePost2() throws NotAuthorizedException, ResourceNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "javax.servlet.http.HttpSession.getAttribute(String)" because "this.httpSession" is null
        //       at com.kay.fashion_blog.service.serviceImplementation.PostServiceImpl.createPost(PostServiceImpl.java:36)
        //   In order to prevent createPost(PostRequestPayload, Long)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createPost(PostRequestPayload, Long).
        //   See https://diff.blue/R013 to resolve this issue.

        PostServiceImpl postServiceImpl = new PostServiceImpl(null, mock(PostsRepository.class),
                mock(CommentsRepository.class), mock(UsersRepository.class), mock(LikesRepository.class),
                mock(CategoryRepository.class));
        postServiceImpl.createPost(new PostRequestPayload("Dr", "Not all who wander are lost"), 123L);
    }

    /**
     * Method under test: {@link PostServiceImpl#retrievePost(Long)}
     */
    @Test
    void testRetrievePost() throws ResourceNotFoundException {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Posts posts = new Posts();
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        ArrayList<Comments> commentsList = new ArrayList<>();
        posts.setComments(commentsList);
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);
        Optional<Posts> ofResult = Optional.of(posts);
        when(postsRepository.findById((Long) any())).thenReturn(ofResult);
        PostResponsePayload actualRetrievePostResult = postServiceImpl.retrievePost(123L);
        assertEquals("Name", actualRetrievePostResult.getAuthor());
        assertEquals("Dr", actualRetrievePostResult.getTitle());
        assertEquals(0, actualRetrievePostResult.getNumberOfLikes().intValue());
        assertEquals(commentsList, actualRetrievePostResult.getLikedBy());
        assertEquals("Not all who wander are lost", actualRetrievePostResult.getContent());
        assertEquals(commentsList, actualRetrievePostResult.getCommentsList());
        assertEquals("Text", actualRetrievePostResult.getCategories());
        verify(postsRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#retrievePost(Long)}
     */
    @Test
    void testRetrievePost2() throws ResourceNotFoundException {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Categories categories1 = new Categories();
        categories1.setId(123L);
        categories1.setText("Text");
        categories1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");
        users1.setRoles(Roles.ADMIN);
        users1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        Posts posts = mock(Posts.class);
        when(posts.getComments()).thenReturn(new ArrayList<>());
        when(posts.getLikes()).thenReturn(new ArrayList<>());
        when(posts.getUsers()).thenReturn(users1);
        when(posts.getCategories()).thenReturn(categories1);
        when(posts.getBody()).thenReturn("Not all who wander are lost");
        when(posts.getTitle()).thenReturn("Dr");
        doNothing().when(posts).setId((Long) any());
        doNothing().when(posts).setTimeCreated((LocalDateTime) any());
        doNothing().when(posts).setTimeUpdated((LocalDateTime) any());
        doNothing().when(posts).setBody((String) any());
        doNothing().when(posts).setCategories((Categories) any());
        doNothing().when(posts).setComments((List<Comments>) any());
        doNothing().when(posts).setLikes((List<Likes>) any());
        doNothing().when(posts).setTitle((String) any());
        doNothing().when(posts).setUsers((Users) any());
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        ArrayList<Comments> commentsList = new ArrayList<>();
        posts.setComments(commentsList);
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);
        Optional<Posts> ofResult = Optional.of(posts);
        when(postsRepository.findById((Long) any())).thenReturn(ofResult);
        PostResponsePayload actualRetrievePostResult = postServiceImpl.retrievePost(123L);
        assertEquals("Name", actualRetrievePostResult.getAuthor());
        assertEquals("Dr", actualRetrievePostResult.getTitle());
        assertEquals(0, actualRetrievePostResult.getNumberOfLikes().intValue());
        assertEquals(commentsList, actualRetrievePostResult.getLikedBy());
        assertEquals("Not all who wander are lost", actualRetrievePostResult.getContent());
        assertEquals(commentsList, actualRetrievePostResult.getCommentsList());
        assertEquals("Text", actualRetrievePostResult.getCategories());
        verify(postsRepository).findById((Long) any());
        verify(posts).getCategories();
        verify(posts).getUsers();
        verify(posts).getBody();
        verify(posts).getTitle();
        verify(posts).getComments();
        verify(posts, atLeast(1)).getLikes();
        verify(posts).setId((Long) any());
        verify(posts).setTimeCreated((LocalDateTime) any());
        verify(posts).setTimeUpdated((LocalDateTime) any());
        verify(posts).setBody((String) any());
        verify(posts).setCategories((Categories) any());
        verify(posts).setComments((List<Comments>) any());
        verify(posts).setLikes((List<Likes>) any());
        verify(posts).setTitle((String) any());
        verify(posts).setUsers((Users) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#retrievePost(Long)}
     */
    @Test
    void testRetrievePost3() throws ResourceNotFoundException {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Categories categories1 = new Categories();
        categories1.setId(123L);
        categories1.setText("Text");
        categories1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");
        users1.setRoles(Roles.ADMIN);
        users1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");
        users2.setRoles(Roles.ADMIN);
        users2.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users2.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Comments comments = new Comments();
        comments.setContent("Not all who wander are lost");
        comments.setId(123L);
        comments.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        comments.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        comments.setUsers(users2);

        ArrayList<Comments> commentsList = new ArrayList<>();
        commentsList.add(comments);
        Posts posts = mock(Posts.class);
        when(posts.getComments()).thenReturn(commentsList);
        when(posts.getLikes()).thenReturn(new ArrayList<>());
        when(posts.getUsers()).thenReturn(users1);
        when(posts.getCategories()).thenReturn(categories1);
        when(posts.getBody()).thenReturn("Not all who wander are lost");
        when(posts.getTitle()).thenReturn("Dr");
        doNothing().when(posts).setId((Long) any());
        doNothing().when(posts).setTimeCreated((LocalDateTime) any());
        doNothing().when(posts).setTimeUpdated((LocalDateTime) any());
        doNothing().when(posts).setBody((String) any());
        doNothing().when(posts).setCategories((Categories) any());
        doNothing().when(posts).setComments((List<Comments>) any());
        doNothing().when(posts).setLikes((List<Likes>) any());
        doNothing().when(posts).setTitle((String) any());
        doNothing().when(posts).setUsers((Users) any());
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        ArrayList<Comments> commentsList1 = new ArrayList<>();
        posts.setComments(commentsList1);
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);
        Optional<Posts> ofResult = Optional.of(posts);
        when(postsRepository.findById((Long) any())).thenReturn(ofResult);
        PostResponsePayload actualRetrievePostResult = postServiceImpl.retrievePost(123L);
        assertEquals("Name", actualRetrievePostResult.getAuthor());
        assertEquals("Dr", actualRetrievePostResult.getTitle());
        assertEquals(0, actualRetrievePostResult.getNumberOfLikes().intValue());
        assertEquals(commentsList1, actualRetrievePostResult.getLikedBy());
        assertEquals("Not all who wander are lost", actualRetrievePostResult.getContent());
        List<CommentResponsePayload> commentsList2 = actualRetrievePostResult.getCommentsList();
        assertEquals(1, commentsList2.size());
        assertEquals("Text", actualRetrievePostResult.getCategories());
        CommentResponsePayload getResult = commentsList2.get(0);
        assertEquals("Not all who wander are lost", getResult.getContent());
        assertEquals("01:01", getResult.getTimeUpdated().toLocalTime().toString());
        assertEquals("01:01", getResult.getTimeCreated().toLocalTime().toString());
        assertEquals("Name", getResult.getCustomerName());
        verify(postsRepository).findById((Long) any());
        verify(posts).getCategories();
        verify(posts).getUsers();
        verify(posts).getBody();
        verify(posts).getTitle();
        verify(posts).getComments();
        verify(posts, atLeast(1)).getLikes();
        verify(posts).setId((Long) any());
        verify(posts).setTimeCreated((LocalDateTime) any());
        verify(posts).setTimeUpdated((LocalDateTime) any());
        verify(posts).setBody((String) any());
        verify(posts).setCategories((Categories) any());
        verify(posts).setComments((List<Comments>) any());
        verify(posts).setLikes((List<Likes>) any());
        verify(posts).setTitle((String) any());
        verify(posts).setUsers((Users) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#retrievePost(Long)}
     */
    @Test
    void testRetrievePost4() throws ResourceNotFoundException {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Categories categories1 = new Categories();
        categories1.setId(123L);
        categories1.setText("Text");
        categories1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");
        users1.setRoles(Roles.ADMIN);
        users1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");
        users2.setRoles(Roles.ADMIN);
        users2.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users2.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Likes likes = new Likes();
        likes.setId(123L);
        likes.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        likes.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        likes.setUsers(users2);

        ArrayList<Likes> likesList = new ArrayList<>();
        likesList.add(likes);
        Posts posts = mock(Posts.class);
        when(posts.getComments()).thenReturn(new ArrayList<>());
        when(posts.getLikes()).thenReturn(likesList);
        when(posts.getUsers()).thenReturn(users1);
        when(posts.getCategories()).thenReturn(categories1);
        when(posts.getBody()).thenReturn("Not all who wander are lost");
        when(posts.getTitle()).thenReturn("Dr");
        doNothing().when(posts).setId((Long) any());
        doNothing().when(posts).setTimeCreated((LocalDateTime) any());
        doNothing().when(posts).setTimeUpdated((LocalDateTime) any());
        doNothing().when(posts).setBody((String) any());
        doNothing().when(posts).setCategories((Categories) any());
        doNothing().when(posts).setComments((List<Comments>) any());
        doNothing().when(posts).setLikes((List<Likes>) any());
        doNothing().when(posts).setTitle((String) any());
        doNothing().when(posts).setUsers((Users) any());
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        ArrayList<Comments> commentsList = new ArrayList<>();
        posts.setComments(commentsList);
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);
        Optional<Posts> ofResult = Optional.of(posts);
        when(postsRepository.findById((Long) any())).thenReturn(ofResult);
        PostResponsePayload actualRetrievePostResult = postServiceImpl.retrievePost(123L);
        assertEquals("Name", actualRetrievePostResult.getAuthor());
        assertEquals("Dr", actualRetrievePostResult.getTitle());
        assertEquals(1, actualRetrievePostResult.getNumberOfLikes().intValue());
        List<String> likedBy = actualRetrievePostResult.getLikedBy();
        assertEquals(1, likedBy.size());
        assertEquals("Name", likedBy.get(0));
        assertEquals("Not all who wander are lost", actualRetrievePostResult.getContent());
        assertEquals(commentsList, actualRetrievePostResult.getCommentsList());
        assertEquals("Text", actualRetrievePostResult.getCategories());
        verify(postsRepository).findById((Long) any());
        verify(posts).getCategories();
        verify(posts).getUsers();
        verify(posts).getBody();
        verify(posts).getTitle();
        verify(posts).getComments();
        verify(posts, atLeast(1)).getLikes();
        verify(posts).setId((Long) any());
        verify(posts).setTimeCreated((LocalDateTime) any());
        verify(posts).setTimeUpdated((LocalDateTime) any());
        verify(posts).setBody((String) any());
        verify(posts).setCategories((Categories) any());
        verify(posts).setComments((List<Comments>) any());
        verify(posts).setLikes((List<Likes>) any());
        verify(posts).setTitle((String) any());
        verify(posts).setUsers((Users) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#editPost(PostRequestPayload, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEditPost() throws NotAuthorizedException, ResourceNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.kay.fashion_blog.constants.Roles.equals(Object)" because "role" is null
        //       at com.kay.fashion_blog.service.serviceImplementation.PostServiceImpl.editPost(PostServiceImpl.java:64)
        //   In order to prevent editPost(PostRequestPayload, Long)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   editPost(PostRequestPayload, Long).
        //   See https://diff.blue/R013 to resolve this issue.

        PostServiceImpl postServiceImpl = new PostServiceImpl(new MockHttpSession(), mock(PostsRepository.class),
                mock(CommentsRepository.class), mock(UsersRepository.class), mock(LikesRepository.class),
                mock(CategoryRepository.class));
        postServiceImpl.editPost(new PostRequestPayload("Dr", "Not all who wander are lost"), 123L);
    }

    /**
     * Method under test: {@link PostServiceImpl#editPost(PostRequestPayload, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEditPost2() throws NotAuthorizedException, ResourceNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "javax.servlet.http.HttpSession.getAttribute(String)" because "this.httpSession" is null
        //       at com.kay.fashion_blog.service.serviceImplementation.PostServiceImpl.editPost(PostServiceImpl.java:63)
        //   In order to prevent editPost(PostRequestPayload, Long)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   editPost(PostRequestPayload, Long).
        //   See https://diff.blue/R013 to resolve this issue.

        PostServiceImpl postServiceImpl = new PostServiceImpl(null, mock(PostsRepository.class),
                mock(CommentsRepository.class), mock(UsersRepository.class), mock(LikesRepository.class),
                mock(CategoryRepository.class));
        postServiceImpl.editPost(new PostRequestPayload("Dr", "Not all who wander are lost"), 123L);
    }

    /**
     * Method under test: {@link PostServiceImpl#deletePost(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeletePost() throws NotAuthorizedException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.kay.fashion_blog.constants.Roles.equals(Object)" because "role" is null
        //       at com.kay.fashion_blog.service.serviceImplementation.PostServiceImpl.deletePost(PostServiceImpl.java:82)
        //   In order to prevent deletePost(Long)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   deletePost(Long).
        //   See https://diff.blue/R013 to resolve this issue.

        (new PostServiceImpl(new MockHttpSession(), mock(PostsRepository.class), mock(CommentsRepository.class),
                mock(UsersRepository.class), mock(LikesRepository.class), mock(CategoryRepository.class))).deletePost(123L);
    }

    /**
     * Method under test: {@link PostServiceImpl#deletePost(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeletePost2() throws NotAuthorizedException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "javax.servlet.http.HttpSession.getAttribute(String)" because "this.httpSession" is null
        //       at com.kay.fashion_blog.service.serviceImplementation.PostServiceImpl.deletePost(PostServiceImpl.java:81)
        //   In order to prevent deletePost(Long)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   deletePost(Long).
        //   See https://diff.blue/R013 to resolve this issue.

        (new PostServiceImpl(null, mock(PostsRepository.class), mock(CommentsRepository.class), mock(UsersRepository.class),
                mock(LikesRepository.class), mock(CategoryRepository.class))).deletePost(123L);
    }

    /**
     * Method under test: {@link PostServiceImpl#viewAllPosts()}
     */
    @Test
    void testViewAllPosts() {
        when(postsRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(postServiceImpl.viewAllPosts().isEmpty());
        verify(postsRepository).findAll();
    }

    /**
     * Method under test: {@link PostServiceImpl#viewAllPosts()}
     */
    @Test
    void testViewAllPosts2() {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Posts posts = new Posts();
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        posts.setComments(new ArrayList<>());
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);

        ArrayList<Posts> postsList = new ArrayList<>();
        postsList.add(posts);
        when(postsRepository.findAll()).thenReturn(postsList);
        assertEquals(1, postServiceImpl.viewAllPosts().size());
        verify(postsRepository).findAll();
    }

    /**
     * Method under test: {@link PostServiceImpl#viewAllPosts()}
     */
    @Test
    void testViewAllPosts3() {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Categories categories1 = new Categories();
        categories1.setId(123L);
        categories1.setText("Text");
        categories1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");
        users1.setRoles(Roles.ADMIN);
        users1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        Posts posts = mock(Posts.class);
        when(posts.getComments()).thenReturn(new ArrayList<>());
        when(posts.getLikes()).thenReturn(new ArrayList<>());
        when(posts.getUsers()).thenReturn(users1);
        when(posts.getCategories()).thenReturn(categories1);
        when(posts.getBody()).thenReturn("Not all who wander are lost");
        when(posts.getTitle()).thenReturn("Dr");
        doNothing().when(posts).setId((Long) any());
        doNothing().when(posts).setTimeCreated((LocalDateTime) any());
        doNothing().when(posts).setTimeUpdated((LocalDateTime) any());
        doNothing().when(posts).setBody((String) any());
        doNothing().when(posts).setCategories((Categories) any());
        doNothing().when(posts).setComments((List<Comments>) any());
        doNothing().when(posts).setLikes((List<Likes>) any());
        doNothing().when(posts).setTitle((String) any());
        doNothing().when(posts).setUsers((Users) any());
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        posts.setComments(new ArrayList<>());
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);

        ArrayList<Posts> postsList = new ArrayList<>();
        postsList.add(posts);
        when(postsRepository.findAll()).thenReturn(postsList);
        assertEquals(1, postServiceImpl.viewAllPosts().size());
        verify(postsRepository).findAll();
        verify(posts).getCategories();
        verify(posts).getUsers();
        verify(posts).getBody();
        verify(posts).getTitle();
        verify(posts).getComments();
        verify(posts, atLeast(1)).getLikes();
        verify(posts).setId((Long) any());
        verify(posts).setTimeCreated((LocalDateTime) any());
        verify(posts).setTimeUpdated((LocalDateTime) any());
        verify(posts).setBody((String) any());
        verify(posts).setCategories((Categories) any());
        verify(posts).setComments((List<Comments>) any());
        verify(posts).setLikes((List<Likes>) any());
        verify(posts).setTitle((String) any());
        verify(posts).setUsers((Users) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#viewAllPosts()}
     */
    @Test
    void testViewAllPosts4() {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Categories categories1 = new Categories();
        categories1.setId(123L);
        categories1.setText("Text");
        categories1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");
        users1.setRoles(Roles.ADMIN);
        users1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");
        users2.setRoles(Roles.ADMIN);
        users2.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users2.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Comments comments = new Comments();
        comments.setContent("Not all who wander are lost");
        comments.setId(123L);
        comments.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        comments.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        comments.setUsers(users2);

        ArrayList<Comments> commentsList = new ArrayList<>();
        commentsList.add(comments);
        Posts posts = mock(Posts.class);
        when(posts.getComments()).thenReturn(commentsList);
        when(posts.getLikes()).thenReturn(new ArrayList<>());
        when(posts.getUsers()).thenReturn(users1);
        when(posts.getCategories()).thenReturn(categories1);
        when(posts.getBody()).thenReturn("Not all who wander are lost");
        when(posts.getTitle()).thenReturn("Dr");
        doNothing().when(posts).setId((Long) any());
        doNothing().when(posts).setTimeCreated((LocalDateTime) any());
        doNothing().when(posts).setTimeUpdated((LocalDateTime) any());
        doNothing().when(posts).setBody((String) any());
        doNothing().when(posts).setCategories((Categories) any());
        doNothing().when(posts).setComments((List<Comments>) any());
        doNothing().when(posts).setLikes((List<Likes>) any());
        doNothing().when(posts).setTitle((String) any());
        doNothing().when(posts).setUsers((Users) any());
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        posts.setComments(new ArrayList<>());
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);

        ArrayList<Posts> postsList = new ArrayList<>();
        postsList.add(posts);
        when(postsRepository.findAll()).thenReturn(postsList);
        assertEquals(1, postServiceImpl.viewAllPosts().size());
        verify(postsRepository).findAll();
        verify(posts).getCategories();
        verify(posts).getUsers();
        verify(posts).getBody();
        verify(posts).getTitle();
        verify(posts).getComments();
        verify(posts, atLeast(1)).getLikes();
        verify(posts).setId((Long) any());
        verify(posts).setTimeCreated((LocalDateTime) any());
        verify(posts).setTimeUpdated((LocalDateTime) any());
        verify(posts).setBody((String) any());
        verify(posts).setCategories((Categories) any());
        verify(posts).setComments((List<Comments>) any());
        verify(posts).setLikes((List<Likes>) any());
        verify(posts).setTitle((String) any());
        verify(posts).setUsers((Users) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#viewAllPosts()}
     */
    @Test
    void testViewAllPosts5() {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Categories categories1 = new Categories();
        categories1.setId(123L);
        categories1.setText("Text");
        categories1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");
        users1.setRoles(Roles.ADMIN);
        users1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");
        users2.setRoles(Roles.ADMIN);
        users2.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users2.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Likes likes = new Likes();
        likes.setId(123L);
        likes.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        likes.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        likes.setUsers(users2);

        ArrayList<Likes> likesList = new ArrayList<>();
        likesList.add(likes);
        Posts posts = mock(Posts.class);
        when(posts.getComments()).thenReturn(new ArrayList<>());
        when(posts.getLikes()).thenReturn(likesList);
        when(posts.getUsers()).thenReturn(users1);
        when(posts.getCategories()).thenReturn(categories1);
        when(posts.getBody()).thenReturn("Not all who wander are lost");
        when(posts.getTitle()).thenReturn("Dr");
        doNothing().when(posts).setId((Long) any());
        doNothing().when(posts).setTimeCreated((LocalDateTime) any());
        doNothing().when(posts).setTimeUpdated((LocalDateTime) any());
        doNothing().when(posts).setBody((String) any());
        doNothing().when(posts).setCategories((Categories) any());
        doNothing().when(posts).setComments((List<Comments>) any());
        doNothing().when(posts).setLikes((List<Likes>) any());
        doNothing().when(posts).setTitle((String) any());
        doNothing().when(posts).setUsers((Users) any());
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        posts.setComments(new ArrayList<>());
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);

        ArrayList<Posts> postsList = new ArrayList<>();
        postsList.add(posts);
        when(postsRepository.findAll()).thenReturn(postsList);
        assertEquals(1, postServiceImpl.viewAllPosts().size());
        verify(postsRepository).findAll();
        verify(posts).getCategories();
        verify(posts).getUsers();
        verify(posts).getBody();
        verify(posts).getTitle();
        verify(posts).getComments();
        verify(posts, atLeast(1)).getLikes();
        verify(posts).setId((Long) any());
        verify(posts).setTimeCreated((LocalDateTime) any());
        verify(posts).setTimeUpdated((LocalDateTime) any());
        verify(posts).setBody((String) any());
        verify(posts).setCategories((Categories) any());
        verify(posts).setComments((List<Comments>) any());
        verify(posts).setLikes((List<Likes>) any());
        verify(posts).setTitle((String) any());
        verify(posts).setUsers((Users) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#searchPostByTitle(SearchDto)}
     */
    @Test
    void testSearchPostByTitle() {
        when(postsRepository.findPostsByTitleContainsIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        assertTrue(postServiceImpl.searchPostByTitle(new SearchDto("Text")).isEmpty());
        verify(postsRepository).findPostsByTitleContainsIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#searchPostByTitle(SearchDto)}
     */
    @Test
    void testSearchPostByTitle2() {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Posts posts = new Posts();
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        posts.setComments(new ArrayList<>());
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);

        ArrayList<Posts> postsList = new ArrayList<>();
        postsList.add(posts);
        when(postsRepository.findPostsByTitleContainsIgnoreCase((String) any())).thenReturn(postsList);
        assertEquals(1, postServiceImpl.searchPostByTitle(new SearchDto("Text")).size());
        verify(postsRepository).findPostsByTitleContainsIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#searchPostByTitle(SearchDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSearchPostByTitle3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.kay.fashion_blog.dto.SearchDto.getText()" because "searchDto" is null
        //       at com.kay.fashion_blog.service.serviceImplementation.PostServiceImpl.searchPostByTitle(PostServiceImpl.java:100)
        //   In order to prevent searchPostByTitle(SearchDto)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   searchPostByTitle(SearchDto).
        //   See https://diff.blue/R013 to resolve this issue.

        when(postsRepository.findPostsByTitleContainsIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        postServiceImpl.searchPostByTitle(null);
    }

    /**
     * Method under test: {@link PostServiceImpl#searchPostByTitle(SearchDto)}
     */
    @Test
    void testSearchPostByTitle4() {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Categories categories1 = new Categories();
        categories1.setId(123L);
        categories1.setText("Text");
        categories1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");
        users1.setRoles(Roles.ADMIN);
        users1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        Posts posts = mock(Posts.class);
        when(posts.getComments()).thenReturn(new ArrayList<>());
        when(posts.getLikes()).thenReturn(new ArrayList<>());
        when(posts.getUsers()).thenReturn(users1);
        when(posts.getCategories()).thenReturn(categories1);
        when(posts.getBody()).thenReturn("Not all who wander are lost");
        when(posts.getTitle()).thenReturn("Dr");
        doNothing().when(posts).setId((Long) any());
        doNothing().when(posts).setTimeCreated((LocalDateTime) any());
        doNothing().when(posts).setTimeUpdated((LocalDateTime) any());
        doNothing().when(posts).setBody((String) any());
        doNothing().when(posts).setCategories((Categories) any());
        doNothing().when(posts).setComments((List<Comments>) any());
        doNothing().when(posts).setLikes((List<Likes>) any());
        doNothing().when(posts).setTitle((String) any());
        doNothing().when(posts).setUsers((Users) any());
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        posts.setComments(new ArrayList<>());
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);

        ArrayList<Posts> postsList = new ArrayList<>();
        postsList.add(posts);
        when(postsRepository.findPostsByTitleContainsIgnoreCase((String) any())).thenReturn(postsList);
        assertEquals(1, postServiceImpl.searchPostByTitle(new SearchDto("Text")).size());
        verify(postsRepository).findPostsByTitleContainsIgnoreCase((String) any());
        verify(posts).getCategories();
        verify(posts).getUsers();
        verify(posts).getBody();
        verify(posts).getTitle();
        verify(posts).getComments();
        verify(posts, atLeast(1)).getLikes();
        verify(posts).setId((Long) any());
        verify(posts).setTimeCreated((LocalDateTime) any());
        verify(posts).setTimeUpdated((LocalDateTime) any());
        verify(posts).setBody((String) any());
        verify(posts).setCategories((Categories) any());
        verify(posts).setComments((List<Comments>) any());
        verify(posts).setLikes((List<Likes>) any());
        verify(posts).setTitle((String) any());
        verify(posts).setUsers((Users) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#searchPostByTitle(SearchDto)}
     */
    @Test
    void testSearchPostByTitle5() {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Categories categories1 = new Categories();
        categories1.setId(123L);
        categories1.setText("Text");
        categories1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");
        users1.setRoles(Roles.ADMIN);
        users1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");
        users2.setRoles(Roles.ADMIN);
        users2.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users2.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Comments comments = new Comments();
        comments.setContent("Not all who wander are lost");
        comments.setId(123L);
        comments.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        comments.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        comments.setUsers(users2);

        ArrayList<Comments> commentsList = new ArrayList<>();
        commentsList.add(comments);
        Posts posts = mock(Posts.class);
        when(posts.getComments()).thenReturn(commentsList);
        when(posts.getLikes()).thenReturn(new ArrayList<>());
        when(posts.getUsers()).thenReturn(users1);
        when(posts.getCategories()).thenReturn(categories1);
        when(posts.getBody()).thenReturn("Not all who wander are lost");
        when(posts.getTitle()).thenReturn("Dr");
        doNothing().when(posts).setId((Long) any());
        doNothing().when(posts).setTimeCreated((LocalDateTime) any());
        doNothing().when(posts).setTimeUpdated((LocalDateTime) any());
        doNothing().when(posts).setBody((String) any());
        doNothing().when(posts).setCategories((Categories) any());
        doNothing().when(posts).setComments((List<Comments>) any());
        doNothing().when(posts).setLikes((List<Likes>) any());
        doNothing().when(posts).setTitle((String) any());
        doNothing().when(posts).setUsers((Users) any());
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        posts.setComments(new ArrayList<>());
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);

        ArrayList<Posts> postsList = new ArrayList<>();
        postsList.add(posts);
        when(postsRepository.findPostsByTitleContainsIgnoreCase((String) any())).thenReturn(postsList);
        assertEquals(1, postServiceImpl.searchPostByTitle(new SearchDto("Text")).size());
        verify(postsRepository).findPostsByTitleContainsIgnoreCase((String) any());
        verify(posts).getCategories();
        verify(posts).getUsers();
        verify(posts).getBody();
        verify(posts).getTitle();
        verify(posts).getComments();
        verify(posts, atLeast(1)).getLikes();
        verify(posts).setId((Long) any());
        verify(posts).setTimeCreated((LocalDateTime) any());
        verify(posts).setTimeUpdated((LocalDateTime) any());
        verify(posts).setBody((String) any());
        verify(posts).setCategories((Categories) any());
        verify(posts).setComments((List<Comments>) any());
        verify(posts).setLikes((List<Likes>) any());
        verify(posts).setTitle((String) any());
        verify(posts).setUsers((Users) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#searchPostByTitle(SearchDto)}
     */
    @Test
    void testSearchPostByTitle6() {
        Categories categories = new Categories();
        categories.setId(123L);
        categories.setText("Text");
        categories.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users = new Users();
        users.setEmail("jane.doe@example.org");
        users.setId(123L);
        users.setName("Name");
        users.setPassword("iloveyou");
        users.setRoles(Roles.ADMIN);
        users.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Categories categories1 = new Categories();
        categories1.setId(123L);
        categories1.setText("Text");
        categories1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        categories1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users1 = new Users();
        users1.setEmail("jane.doe@example.org");
        users1.setId(123L);
        users1.setName("Name");
        users1.setPassword("iloveyou");
        users1.setRoles(Roles.ADMIN);
        users1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Users users2 = new Users();
        users2.setEmail("jane.doe@example.org");
        users2.setId(123L);
        users2.setName("Name");
        users2.setPassword("iloveyou");
        users2.setRoles(Roles.ADMIN);
        users2.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        users2.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        Likes likes = new Likes();
        likes.setId(123L);
        likes.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        likes.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        likes.setUsers(users2);

        ArrayList<Likes> likesList = new ArrayList<>();
        likesList.add(likes);
        Posts posts = mock(Posts.class);
        when(posts.getComments()).thenReturn(new ArrayList<>());
        when(posts.getLikes()).thenReturn(likesList);
        when(posts.getUsers()).thenReturn(users1);
        when(posts.getCategories()).thenReturn(categories1);
        when(posts.getBody()).thenReturn("Not all who wander are lost");
        when(posts.getTitle()).thenReturn("Dr");
        doNothing().when(posts).setId((Long) any());
        doNothing().when(posts).setTimeCreated((LocalDateTime) any());
        doNothing().when(posts).setTimeUpdated((LocalDateTime) any());
        doNothing().when(posts).setBody((String) any());
        doNothing().when(posts).setCategories((Categories) any());
        doNothing().when(posts).setComments((List<Comments>) any());
        doNothing().when(posts).setLikes((List<Likes>) any());
        doNothing().when(posts).setTitle((String) any());
        doNothing().when(posts).setUsers((Users) any());
        posts.setBody("Not all who wander are lost");
        posts.setCategories(categories);
        posts.setComments(new ArrayList<>());
        posts.setId(123L);
        posts.setLikes(new ArrayList<>());
        posts.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTimeUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        posts.setTitle("Dr");
        posts.setUsers(users);

        ArrayList<Posts> postsList = new ArrayList<>();
        postsList.add(posts);
        when(postsRepository.findPostsByTitleContainsIgnoreCase((String) any())).thenReturn(postsList);
        assertEquals(1, postServiceImpl.searchPostByTitle(new SearchDto("Text")).size());
        verify(postsRepository).findPostsByTitleContainsIgnoreCase((String) any());
        verify(posts).getCategories();
        verify(posts).getUsers();
        verify(posts).getBody();
        verify(posts).getTitle();
        verify(posts).getComments();
        verify(posts, atLeast(1)).getLikes();
        verify(posts).setId((Long) any());
        verify(posts).setTimeCreated((LocalDateTime) any());
        verify(posts).setTimeUpdated((LocalDateTime) any());
        verify(posts).setBody((String) any());
        verify(posts).setCategories((Categories) any());
        verify(posts).setComments((List<Comments>) any());
        verify(posts).setLikes((List<Likes>) any());
        verify(posts).setTitle((String) any());
        verify(posts).setUsers((Users) any());
    }
}


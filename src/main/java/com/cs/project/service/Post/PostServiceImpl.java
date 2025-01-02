package com.cs.project.service.Post;

import com.cs.project.model.Post;
import com.cs.project.repository.PostRepository;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que implementa la interfaz PostService
 * @author camil
 */
@Service
@Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Método que llama a un método de PostRepository para obtener la lista de Posts
     * @return retorna lista de los registros de la tabla Posts
     */
    @Override
    public List<Post> reviewExistentPosts() {
        return postRepository.getAllPosts();
    }

    @Override
    public void reviewDataPostForCreate(String tittle, String content, HttpSession session) {
        int userId = (int) session.getAttribute("userIdLogged");
        Post post = new Post();
        post.setTittle(tittle);
        post.setContent(content);
        post.setCreatedDate( LocalDate.now());
        post.setUserId(userId);
        postRepository.createPost(post);
    }
    
    
}

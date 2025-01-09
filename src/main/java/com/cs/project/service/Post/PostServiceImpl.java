package com.cs.project.service.Post;

import com.cs.project.model.Post;
import com.cs.project.repository.PostRepository;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que implementa la interfaz PostService
 *
 * @author Camillie Ayovi Villafuerte
 */
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Método que obtiene la lista de Posts de la capa de datos
     *
     * @return retorna lista de los registros de la tabla Posts
     */
    @Override
    public List<Post> reviewExistentPosts() {
        return postRepository.getAllPosts();
    }

    /**
     * Método que asigna los valores a los atributos del objeto Post para su registro en la capa de datos
     *
     * @param tittle título del post
     * @param content contenido del post
     * @param session sesión activada por el usuario
     */
    @Override
    public void reviewDataPostForCreate(String tittle, String content, HttpSession session) {
        int userId = (int) session.getAttribute("userIdLogged");
        Post post = new Post();
        post.setTittle(tittle);
        post.setContent(content);
        post.setCreatedDate(LocalDateTime.now());
        post.setUserId(userId);
        postRepository.createPost(post);
    }

    /**
     * Método que valida la existencia de un post según su identificador
     *
     * @param idPost identificador del post a buscar
     * @return retorna el post con el id enviado
     */
    @Override
    public Post reviewExistentPost(int idPost) {
        Post post = postRepository.getPostById(idPost);
        if (post != null) {
            return post;
        } else {
            return null;
        }
    }

    /**
     * Método que recibe el objeto post para su actualización en la capa de datos
     *
     * @param post post a actualizar
     */
    @Override
    public void reviewDataPostForUpdate(Post post) {
        postRepository.updatePost(post);
    }

    /**
     * Método que recibe el identificador del objeto post para su eliminación en la capa de datos
     *
     * @param postId identificador del post a eliminar
     */
    @Override
    public void reviewPostForDelete(int postId) {
        Post post = reviewExistentPost(postId);
        postRepository.deletePost(post);
    }

}

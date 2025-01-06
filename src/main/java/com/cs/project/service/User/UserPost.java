/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cs.project.service.User;

import com.cs.project.model.Post;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public interface UserPost {
    List<Post> getPostsByUser(HttpSession session);
}

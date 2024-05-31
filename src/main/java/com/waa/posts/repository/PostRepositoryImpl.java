package com.waa.posts.repository;

import com.waa.posts.entity.Post;
import com.waa.users.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepositoryMethods{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> findPosts(String authorName, String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> query = cb.createQuery(Post.class);
        Root<Post> post = query.from(Post.class);
        Join<Post, User> author = post.join("author", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (authorName != null && !authorName.isEmpty()) {
            predicates.add(cb.equal(author.get("name"), authorName));
        }

        if (title != null && !title.isEmpty()) {
            predicates.add(cb.like(post.get("title"), "%" + title + "%"));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}

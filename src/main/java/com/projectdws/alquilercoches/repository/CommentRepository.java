package com.projectdws.alquilercoches.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.projectdws.alquilercoches.models.Comment;


@Component
public class CommentRepository {

    // Atomic counter to generate unique IDs in a thread-safe manner.
    private AtomicLong nextId = new AtomicLong(1L);

    // Thread-safe map to store comments using their ID as the key.
    private ConcurrentHashMap<Long, Comment> comments = new ConcurrentHashMap<>();


    public List<Comment> findAll() {
        return comments.values().stream().toList();
    }
    
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(comments.get(id));
    }

    public void save(Comment comment) {
        Long id = comment.getID();
        if (id == 0) {
            id = nextId.getAndIncrement(); // Assigns a new ID if not already set.
            comment.setID(id);
        }
        comments.put((Long) id, comment);
    }

    public void delete(Comment comment) {
        comments.remove(comment.getID());
    }

}

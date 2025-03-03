package com.projectdws.alquilercoches.repository;

import com.projectdws.alquilercoches.models.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Component
public class CommentRepository {

    // Atomic counter to generate unique IDs in a thread-safe manner.
    private AtomicLong nextId = new AtomicLong(1L);

    // Thread-safe map to store comments using their ID as the key.
    private ConcurrentHashMap<Long, Comment> comments = new ConcurrentHashMap<>();

    
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(comments.get(id));
    }

    public void save(Comment comment) {
        int id = comment.getID();
        if (id == 0) {
            id = (int) nextId.getAndIncrement(); // Assigns a new ID if not already set.
            comment.setID(id);
        }
        comments.put((long) id, comment);
    }

    
    @SuppressWarnings("unlikely-arg-type")
    public void delete(Comment comment) {
        comments.remove(comment.getID());
    }

    public List<Comment> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}

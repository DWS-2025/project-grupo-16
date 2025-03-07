package com.projectdws.alquilercoches.services;

import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.Comment;
import com.projectdws.alquilercoches.repository.CarRepository;
import com.projectdws.alquilercoches.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public void save(Car car, Comment comment) {
        comment.setCarCommented(car);  
        commentRepository.save(comment);
    }

    public void delete(Long commentId, Car car) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            car.getComments().remove(comment.get()); // Remover de la lista del coche
            CarRepository.save(car);  // Guardar el coche sin el comentario
            commentRepository.delete(comment.get());  // Eliminar de la BD
        }
    }
}

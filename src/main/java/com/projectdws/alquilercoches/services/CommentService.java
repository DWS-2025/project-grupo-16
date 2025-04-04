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

    @Autowired
    private CarRepository carRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public void save(Comment comment) { 
        commentRepository.save(comment);
    }

    public void delete(Long commentId) {
        Optional<Comment> opComment = commentRepository.findById(commentId);
        Car car = opComment.get().getCarCommented();
        if (opComment.isPresent()) {
            car.getComments().remove(opComment.get()); // Remove comment from list
            carRepository.save(car);  // Update car without the comment
            commentRepository.delete(opComment.get());  // Delete comment from Data Base
        }
    }
}

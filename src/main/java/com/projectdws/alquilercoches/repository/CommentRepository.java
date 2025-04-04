package com.projectdws.alquilercoches.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.projectdws.alquilercoches.models.Comment;


@Component
public interface CommentRepository extends JpaRepository <Comment, Long>{

}

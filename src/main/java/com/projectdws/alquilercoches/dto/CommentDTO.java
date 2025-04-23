package com.projectdws.alquilercoches.dto;

import com.projectdws.alquilercoches.models.Comment;

public class CommentDTO {

    private String authorName;
    private int numberStars;
    private String message;

    public CommentDTO(Comment comment) {
        this.authorName = comment.getAuthorName();
        this.numberStars = comment.getNumberStars();
        this.message = comment.getMessage();
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getNumberStars() {
        return numberStars;
    }

    public String getMessage() {
        return message;
    }
}
package com.projectdws.alquilercoches.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projectdws.alquilercoches.models.Comment;
import com.projectdws.alquilercoches.models.Car;
import com.projectdws.alquilercoches.models.Dealership;
import com.projectdws.alquilercoches.services.CarService;
import com.projectdws.alquilercoches.services.CommentService;
import com.projectdws.alquilercoches.services.DealershipService;
import com.projectdws.alquilercoches.services.UserService;

@Controller
public class CarsController {

    @Autowired
	private CarService carService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

    @Autowired
	private DealershipService dealershipService;

	@GetMapping("/cars")
	public String getCars(Model model){
		model.addAttribute("dealerships", carService.findAll());
		return "cars";
	}

	@PostMapping("/cars/new")
	public String newPost(Model model, Car car) {
		carService.save(car);
		return "new_car";
	}

	@GetMapping("/cars/{id}")
	public String getPost(Model model, @PathVariable long id) {
		Optional<Car> car = carService.findById(id);
		if (post.isPresent()) {
			model.addAttribute("post", post.get());
			String likedText = userService.isPostLikedByCurrentUser(post.get()) ? "Unlike" : "Like";
			model.addAttribute("likedText", likedText);
			return "show_post";
		} else {
			return "post_not_found";
		}
	}

	@GetMapping("/posts/{id}/edit")
	public String editPost(Model model, @PathVariable long id) {
		Optional<Post> post = postService.findById(id);
		if (post.isPresent()) {
			model.addAttribute("post", post.get());
			return "edit_post";
		} else {
			return "post_not_found";
		}
	}

	@PostMapping("/posts/{id}/edit")
	public String updatePost(Model model, @PathVariable long id, Post updatedPost) {
		Optional<Post> op = postService.findById(id);
		if (op.isPresent()) {
			Post oldPost = op.get();
			postService.update(oldPost, updatedPost);
			return "redirect:/posts/" + id;
		} else {
			return "post_not_found";
		}
	}

	@PostMapping("/posts/{id}/delete")
	public String deletePost(@PathVariable long id) {
		Optional<Post> op = postService.findById(id);
		if (op.isPresent()) {
			postService.delete(op.get());
			return "redirect:/";
		} else {
			return "post_not_found";
		}
	}

	@PostMapping("/posts/{postId}/comments/new")
	public String newComment(@PathVariable long postId, Comment comment) {
		Optional<Post> op = postService.findById(postId);
		if (op.isPresent()) {
			Post post = op.get();
			commentService.save(post, comment);
			return "redirect:/posts/" + postId;
		} else {
			return "post_not_found";
		}
	}

	@PostMapping("/posts/{postId}/comments/{commentId}/delete")
	public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {

		Optional<Post> op = postService.findById(postId);

		if (op.isPresent()) {
			Post post = op.get();
			commentService.delete(commentId, post);
			return "redirect:/posts/" + postId;
		} else {
			return "post_not_found";
		}
		
	}

	@PostMapping("/posts/{postId}/likeToggle")
	public String likePost(@PathVariable Long postId, Long userId) {
		Optional<Post> op = postService.findById(postId);
		if (op.isPresent()) {
			Post post = op.get();
			userService.likeOrUnlikePost(userId, post);
			return "redirect:/posts/" + postId;
		} else {
			return "post_not_found";
		}
	}
    
}

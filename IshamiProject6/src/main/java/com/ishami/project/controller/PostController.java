package com.ishami.project.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ishami.project.model.Post;
import com.ishami.project.repository.PostRepository;
import com.ishami.project.service.PostService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	// Search All post list
	@GetMapping
	public String getAllPosts(Model model) {
		model.addAttribute("posts", postService.getAllPosts());

		return "post/list"; // response list.html template
	}

	@GetMapping("/new")
	public String createPostForm(Model model) {
		model.addAttribute("post", new Post()); // Add empty Post object
		return "post/create"; // Ensure posts/create.html
	}

	// Create Posts
	@PostMapping("/new")
	public String createPost(@Valid Post post, BindingResult result) {
		if (result.hasErrors()) {
			return "post/create"; // Return to the form if validation fails
		}
		postService.createPost(post);
		return "redirect:/posts";
	}

	// Go to Detail Posts
	@GetMapping("/detail/{id}")
	public String detailPostForm(@PathVariable ObjectId id, Model model) {
		Post post = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
		model.addAttribute("post", post);
		return "post/detail";
	}

	// Go to Update Posts Form
	@GetMapping("/edit/{id}")
	public String editPostForm(@PathVariable ObjectId id, Model model) {
		Post post = postService.getPostById(id).orElseThrow(() 
				-> new RuntimeException("Post not found"));
		
		model.addAttribute("myform", post); 
		System.out.println(model.asMap());
		return "post/edit"; // edit.html
	}

	// Update Post
	@PostMapping("/edit/{id}")
	public String editPost(@PathVariable ObjectId id, 
							@Valid @ModelAttribute("myform") Post post, 
							BindingResult result) {
		if (result.hasErrors()) {
			return "post/edit";
		}
		postService.updatePost(id, post);
		return "redirect:/posts";
	}

	// Delete Post
	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable ObjectId id) {
		postService.deletePost(id);
		return "redirect:/posts";
	}

	// Add Comment
	@PostMapping("/{postId}/comments")
	public void addComment(@PathVariable ObjectId postId, @RequestParam String userId, @RequestParam String content) {
		postService.addComment(postId, userId, content);
	}

	// Delete Comment
	@DeleteMapping("/{postId}/comments/{commentId}")
	public void deleteComment(@PathVariable ObjectId postId, @PathVariable String commentId) {
		postService.deleteComment(postId, commentId);
	}

	// Update Comment
	@PutMapping("/{postId}/comments")
	public void updateComment(@PathVariable ObjectId postId, @RequestParam String userId,
			@RequestParam String oldContent, @RequestParam String newContent) {
		postService.updateComment(postId, userId, oldContent, newContent);
	}

}

package com.ishami.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ishami.project.model.Comment;
import com.ishami.project.model.Post;
import com.ishami.project.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	// Create Post
	public Post createPost(Post post) {
		post.setRegdate(LocalDateTime.now());
		return postRepository.save(post);
	}
	
	// Search All Post
	public List<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
	// Search a post
	public Optional<Post> getPostById(ObjectId id){
		return postRepository.findById(id);
	}
	
	// Edit post
	public Post updatePost(ObjectId id, Post postDetails) {
		Post post = postRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Post not Found"));
		post.setTitle(postDetails.getTitle());
		post.setContent(postDetails.getContent());
		return postRepository.save(post);
	}
	
	// Delete Post
	public void deletePost(ObjectId id) {
		postRepository.deleteById(id);
	}
	
	// Comment Add
	public void addComment(ObjectId postId, String userId, String content) {
		Optional<Post> postOptional = postRepository.findById(postId);
		if(postOptional.isPresent()) {
			Post post = postOptional.get();
			Comment comment = new Comment(UUID.randomUUID().toString(), 
					userId, content, LocalDateTime.now());
			post.addComment(comment);
			postRepository.save(post); // Save updated post with the new comment
		} else {
			throw new RuntimeException("Post not found");
		}
	}
	
	// Edit Comment
	public void updateComment(ObjectId postId, String userId, 
			String oldContent, String newContent) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new RuntimeException("Post not found"));
		
		post.getComments().forEach(comment -> {
			if(comment.getUserId().equals(userId) && 
					comment.getContent().equals(oldContent)) {
				comment.setContent(newContent);
				comment.setRegdate(LocalDateTime.now());
			}
		});
		
		postRepository.save(post);
	}
	
	// Delete Comment
	public void deleteComment(ObjectId postId, String commentId) {
		Optional<Post> postOptional = postRepository.findById(postId);
		if(postOptional.isPresent()) {
			Post post = postOptional.get();
			post.removeCommentById(commentId);
			postRepository.save(post);
		} else {
			throw new RuntimeException("Post not found");
		}
	}
	
	
	
}

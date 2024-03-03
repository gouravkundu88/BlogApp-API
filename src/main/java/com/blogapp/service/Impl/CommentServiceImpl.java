package com.blogapp.service.Impl;

import com.blogapp.entity.Comment;
import com.blogapp.entity.Post;
import com.blogapp.payload.CommentDto;
import com.blogapp.payload.PostDto;
import com.blogapp.payload.PostWithCommentDto;
import com.blogapp.repository.CommentRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    Comment mapToEntity(CommentDto dto){
        Comment comment = modelMapper.map(dto, Comment.class);
        return comment;
    }

    CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

    public PostWithCommentDto getAllCommentsByPostId(long id){
        Post post = postRepository.findById(id).get();

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());

        PostWithCommentDto postWithCommentDto =new PostWithCommentDto();
        postWithCommentDto.setCommentDto(dtos);
        postWithCommentDto.setPost(dto);

        return postWithCommentDto;
    }
}

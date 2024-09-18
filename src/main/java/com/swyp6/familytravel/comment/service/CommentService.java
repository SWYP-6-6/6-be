package com.swyp6.familytravel.comment.service;

import com.swyp6.familytravel.comment.dto.CommentRequest;
import com.swyp6.familytravel.comment.dto.CommentResponse;
import com.swyp6.familytravel.comment.entity.Comment;
import com.swyp6.familytravel.comment.repository.CommentRepository;
import com.swyp6.familytravel.feed.entity.Feed;
import com.swyp6.familytravel.feed.repository.FeedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final FeedRepository feedRepository;

    public CommentResponse createComment(Long feedId, CommentRequest commentRequest) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(() -> new IllegalArgumentException("해당 피드가 존재하지 않습니다."));
        Comment comment = commentRequest.toComment(feed);
        commentRepository.save(comment);
        return new CommentResponse(comment);
    }

    public CommentResponse updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        comment.updateComment(content);
        return new CommentResponse(comment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public CommentResponse addLike(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        comment.addLikeCnt();
        return new CommentResponse(comment);
    }
}

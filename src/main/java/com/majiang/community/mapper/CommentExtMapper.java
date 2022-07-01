package com.majiang.community.mapper;

import com.majiang.community.model.Comment;
import com.majiang.community.model.CommentExample;
import com.majiang.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}
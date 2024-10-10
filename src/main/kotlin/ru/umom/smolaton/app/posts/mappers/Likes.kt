package ru.umom.smolaton.app.posts.mappers

import ru.umom.smolaton.app.posts.dto.CommentRs
import ru.umom.smolaton.app.posts.dto.LikeRs
import ru.umom.smolaton.app.posts.entity.Comment
import ru.umom.smolaton.app.posts.entity.Like


fun Like.toDto(): LikeRs = LikeRs(
    likeId = id,
    postId = post.id,
    userId = userId,
)


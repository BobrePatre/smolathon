package ru.umom.smolaton.app.posts.mappers

import ru.umom.smolaton.app.posts.dto.CommentRs
import ru.umom.smolaton.app.posts.entity.Comment


fun Comment.toDto(): CommentRs = CommentRs(
    id = id,
    body = body,
    userId = userId,
)


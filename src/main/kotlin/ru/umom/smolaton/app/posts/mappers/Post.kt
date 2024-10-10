package ru.umom.smolaton.app.posts.mappers

import ru.umom.smolaton.app.posts.dto.PostRs
import ru.umom.smolaton.app.posts.entity.Post

fun Post.toDto(): PostRs = PostRs(
    body = body,
    fileIds = fileIds,
    likes = likes.map { it.toDto() },
    comments = comments.map { it.toDto() },
    id = id
)
package ru.umom.smolaton.app.posts.mappers

import ru.umom.smolaton.app.posts.dto.PostRs
import ru.umom.smolaton.app.posts.entity.Post
import ru.umom.smolaton.app.profile.dto.GetProfileInfoRs
import ru.umom.smolaton.app.profile.entity.Profile

fun Post.toDto(profile: Profile): PostRs = PostRs(
    body = body,
    fileIds = fileIds,
    likes = likes.map { it.toDto() },
    comments = comments.map { it.toDto() },
    id = id,
    author = GetProfileInfoRs(
        name = profile.name,
        surname = profile.surname,
        avatarId = profile.avatarId,
        userInterests = profile.interests,
        wantToFind = profile.wantToFind,
        userId = profile.id
    )
)
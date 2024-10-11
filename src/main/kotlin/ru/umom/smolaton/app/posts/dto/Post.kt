package ru.umom.smolaton.app.posts.dto

import ru.umom.smolaton.app.profile.dto.GetProfileInfoRs


class CreatePostRq(
    val body: String,
    val fileIds: MutableList<String>,
)

class PostRs(
    val id: String,
    val author: GetProfileInfoRs,
    val body: String,
    val fileIds: MutableList<String>,
    val likes: List<LikeRs>,
    val comments: List<CommentRs>,
)
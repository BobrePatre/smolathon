package ru.umom.smolaton.app.posts.dto



class CreatePostRq(
    val body: String,
    val fileIds: MutableList<String>,
)

class PostRs(
    val id: String,
    val body: String,
    val fileIds: MutableList<String>,
    val likes: List<LikeRs>,
    val comments: List<CommentRs>,
)
package ru.umom.smolaton.app.posts.dto


class CommentRs(
    val id: String,
    val userId: String,
    val body: String,
)

class CommentPostRq(
    val postId: String,
    val body: String,
)
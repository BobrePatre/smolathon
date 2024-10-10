package ru.umom.smolaton.app.posts.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "comments")
class Comment(

    @Id
    var id: String = UUID.randomUUID().toString(),

    @Column(length = 1000)
    var body: String,

    val userId: String,

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post
)
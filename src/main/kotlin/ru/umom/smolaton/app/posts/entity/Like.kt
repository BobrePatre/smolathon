package ru.umom.smolaton.app.posts.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "likes")
class Like(

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post,

    val userId: String,
) {
    @Id
    var id: String = UUID.randomUUID().toString()
}
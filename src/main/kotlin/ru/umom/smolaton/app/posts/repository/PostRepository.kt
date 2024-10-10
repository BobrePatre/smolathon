package ru.umom.smolaton.app.posts.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.umom.smolaton.app.posts.entity.Post

@Repository
interface PostRepository : JpaRepository<Post, String> {
    fun findAllByUserId(userId: String): MutableList<Post>
}
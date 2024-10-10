package ru.umom.smolaton.app.posts.entity

import jakarta.persistence.*
import ru.umom.smolaton.app.profile.entity.Profile
import java.util.*

@Entity
@Table(name = "posts")
class Post(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    var profile: Profile,

    val userId: String,

    @ElementCollection
    @CollectionTable(name = "post_file_ids", joinColumns = [JoinColumn(name = "post_id")])
    @Column(name = "file_id")
    val fileIds: MutableList<String> = mutableListOf(),

    @Column(length = 5000)
    val body: String,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    var likes: MutableList<Like> = mutableListOf(),

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf()
) {
    @Id
    var id: String = UUID.randomUUID().toString()
}

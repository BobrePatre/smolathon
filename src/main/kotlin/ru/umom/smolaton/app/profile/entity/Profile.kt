package ru.umom.smolaton.app.profile.entity

import jakarta.persistence.*
import ru.umom.smolaton.app.posts.entity.Post
import ru.umom.smolaton.shared.enums.Interests
import ru.umom.smolaton.shared.enums.WantToFind
import java.util.*

@Entity
@Table(name = "profiles")
class Profile(
    @OneToMany(mappedBy = "profile", cascade = [CascadeType.ALL], orphanRemoval = true)
    var posts: MutableList<Post> = mutableListOf(),  // List of Posts by the Profile

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profile_interests", joinColumns = [JoinColumn(name = "profile_id")])
    @Enumerated(EnumType.STRING)
    val interests: MutableList<Interests>,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profile_want_to_find", joinColumns = [JoinColumn(name = "profile_id")])
    @Enumerated(EnumType.STRING)
    val wantToFind: MutableList<WantToFind>,

    val isActive: Boolean,

    val name: String,
    val surname: String,
    val avatarId: String,

    @Id
    val id: String,
)

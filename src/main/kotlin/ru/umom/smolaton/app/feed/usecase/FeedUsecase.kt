package ru.umom.smolaton.app.feed.usecase

import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.umom.smolaton.app.posts.dto.PostRs
import ru.umom.smolaton.app.posts.mappers.toDto
import ru.umom.smolaton.app.posts.repository.PostRepository
import ru.umom.smolaton.app.profile.dto.GetProfileInfoRs
import ru.umom.smolaton.app.profile.repository.ProfileRepository
import ru.umom.smolaton.shared.errors.common.NotFoundError


@Service
class FeedUsecase(
    private val postsRepository: PostRepository,
    private val profilesRepository: ProfileRepository,
) {


    @Transactional
    fun getFeedForUser(jwt: Jwt): MutableList<PostRs> {
        val user = profilesRepository.findByIdOrNull(jwt.subject) ?: throw NotFoundError("User not found")
        val usersWithEquailInterests =
            profilesRepository.findUsersWithCommonInterests(interests = user.interests, profileId = jwt.subject)
        val recomendetPosts: MutableList<PostRs> = mutableListOf()
        usersWithEquailInterests.forEach {
            it.posts.forEach { post ->
                recomendetPosts.add(
                    PostRs(
                        id = post.id,
                        body = post.body,
                        fileIds = post.fileIds,
                        likes = post.likes.map { like -> like.toDto() },
                        comments = post.comments.map { comment -> comment.toDto() },
                        author = GetProfileInfoRs(
                            name = it.name,
                            userId = it.id,
                            userInterests = it.interests,
                            wantToFind = it.wantToFind,
                            surname = it.surname,
                            avatarId = it.avatarId,
                        )
                    )
                )
            }
        }
        return recomendetPosts
    }

}
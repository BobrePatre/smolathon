package ru.umom.smolaton.app.posts.usecase

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import ru.umom.smolaton.app.posts.dto.CommentPostRq
import ru.umom.smolaton.app.posts.dto.CreatePostRq
import ru.umom.smolaton.app.posts.dto.PostRs
import ru.umom.smolaton.app.posts.entity.Comment
import ru.umom.smolaton.app.posts.entity.Like
import ru.umom.smolaton.app.posts.entity.Post
import ru.umom.smolaton.app.posts.mappers.toDto
import ru.umom.smolaton.app.posts.repository.PostRepository
import ru.umom.smolaton.app.profile.entity.Profile
import ru.umom.smolaton.app.profile.repository.ProfileRepository
import ru.umom.smolaton.shared.errors.common.AlreadyExistsError
import ru.umom.smolaton.shared.errors.common.NotFoundError

@Service
class PostUsecase(
    private val postRepository: PostRepository,
    private val profileRepository: ProfileRepository,
) {

    @Transactional
    fun create(jwt: Jwt, dto: CreatePostRq) {
        val profile: Profile = profileRepository.findByIdOrNull(jwt.subject) ?: throw NotFoundError("User not found")
        postRepository.save(
            Post(
                userId = jwt.subject,
                body = dto.body,
                fileIds = dto.fileIds,
                profile = profile,
            )
        )
    }

    @Transactional
    fun getMyPosts(jwt: Jwt): List<PostRs> {
        val profile: Profile = profileRepository.findByIdOrNull(jwt.subject) ?: throw NotFoundError("User not found")
        return postRepository.findAllByUserId(jwt.subject).map { it.toDto(profile) }
    }

    @Transactional
    fun likePost(jwt: Jwt, postId: String) {
        postRepository.findByIdOrNull(postId)?.let {
            for (like in it.likes) {
                if (like.userId == jwt.subject) {
                    throw AlreadyExistsError("Already liked")
                }
            }
            it.likes.add(
                Like(
                    userId = jwt.subject,
                    post = it
                )
            )
        } ?: throw NotFoundError("Post not found: $postId")
    }


    @Transactional
    fun commentPost(jwt: Jwt, dto: CommentPostRq) {
        postRepository.findByIdOrNull(dto.postId)?.let {
            it.comments.add(
                Comment(
                    userId = jwt.subject,
                    body = dto.body,
                    post = it
                )
            )
        } ?: NotFoundError("Post not found: $dto.postId")
    }
}
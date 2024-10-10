package ru.umom.smolaton.app.posts.delivery

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*
import ru.umom.smolaton.app.posts.dto.CommentPostRq
import ru.umom.smolaton.app.posts.dto.CreatePostRq
import ru.umom.smolaton.app.posts.dto.PostRs
import ru.umom.smolaton.app.posts.usecase.PostUsecase

@RestController
@RequestMapping("/posts")
class PostsController(
    private val postUsecase: PostUsecase,
) {

    @PostMapping
    fun create(@RequestBody dto: CreatePostRq, @AuthenticationPrincipal jwt: Jwt) = postUsecase.create(jwt, dto)

    @GetMapping
    fun getMyPosts(@AuthenticationPrincipal jwt: Jwt): List<PostRs> = postUsecase.getMyPosts(jwt)

    @PutMapping("/like/{postId}")
    fun likePost(@AuthenticationPrincipal jwt: Jwt, @PathVariable postId: String) = postUsecase.likePost(jwt, postId)

    @PutMapping("/comment")
    fun commentPost(@AuthenticationPrincipal jwt: Jwt, @RequestBody dto: CommentPostRq) =
        postUsecase.commentPost(jwt, dto)
}
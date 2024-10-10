package ru.umom.smolaton.app.feed.delivery

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.umom.smolaton.app.feed.usecase.FeedUsecase
import ru.umom.smolaton.app.posts.dto.PostRs

@RestController
@RequestMapping("/feed")
class FeedController(
    private val feedUsecase: FeedUsecase
) {


    @GetMapping
    fun getFeed(@AuthenticationPrincipal jwt: Jwt): MutableList<PostRs> = feedUsecase.getFeedForUser(jwt)
}
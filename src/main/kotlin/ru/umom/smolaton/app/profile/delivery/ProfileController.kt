package ru.umom.smolaton.app.profile.delivery

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*
import ru.umom.smolaton.app.profile.dto.CreateProfileInfoRq
import ru.umom.smolaton.app.profile.dto.GetProfileInfoRs
import ru.umom.smolaton.app.profile.usecase.ProfileUsecase

@RestController
@RequestMapping("/profiles")
class ProfileController(
    private val profileUsecase: ProfileUsecase
) {

    @GetMapping
    fun getProfile(@AuthenticationPrincipal jwt: Jwt): GetProfileInfoRs = profileUsecase.getInfo(jwt)

    @PostMapping
    fun createProfile(@AuthenticationPrincipal jwt: Jwt, @RequestBody dto: CreateProfileInfoRq) =
        profileUsecase.createInfo(jwt, dto)

    @GetMapping("/{userId}")
    fun getInfoAboutUserById(@PathVariable userId: String): GetProfileInfoRs =
        profileUsecase.getInfoAboutUserById(userId)
}
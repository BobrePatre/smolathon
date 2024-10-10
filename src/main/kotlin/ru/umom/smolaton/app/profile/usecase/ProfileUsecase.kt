package ru.umom.smolaton.app.profile.usecase

import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.umom.smolaton.app.profile.dto.CreateProfileInfoRq
import ru.umom.smolaton.app.profile.dto.GetProfileInfoRs
import ru.umom.smolaton.app.profile.entity.Profile
import ru.umom.smolaton.app.profile.repository.ProfileRepository
import ru.umom.smolaton.shared.errors.common.AlreadyExistsError
import ru.umom.smolaton.shared.errors.common.NotFoundError


@Service
class ProfileUsecase(
    private val profileRepository: ProfileRepository,
) {

    @Transactional
    fun getInfo(jwt: Jwt): GetProfileInfoRs = profileRepository.findByIdOrNull(jwt.subject)?.let {
        GetProfileInfoRs(
            userId = it.id,
            userInterests = it.interests,
            wantToFind = it.wantToFind,
            name = it.name,
            surname = it.surname,
            avatarId = it.avatarId,
        )
    } ?: throw NotFoundError("User Not Found")


    @Transactional
    fun createInfo(jwt: Jwt, dto: CreateProfileInfoRq) {
        profileRepository.findByIdOrNull(jwt.subject)?.let {
            throw AlreadyExistsError("User Already Exists")
        }
        profileRepository.save(
            Profile(
                id = jwt.subject,
                isActive = true,
                interests = dto.userInterests,
                wantToFind = dto.wantToFind,
                avatarId = dto.avatarId,
                name = dto.name,
                surname = dto.surname,
            )
        )
    }


    @Transactional
    fun getInfoAboutUserById(userId: String): GetProfileInfoRs = profileRepository.findByIdOrNull(userId)?.let {
        GetProfileInfoRs(
            userId = it.id,
            userInterests = it.interests,
            wantToFind = it.wantToFind,
            name = it.name,
            surname = it.surname,
            avatarId = it.avatarId,
        )
    } ?: throw NotFoundError("User Not Found")

}
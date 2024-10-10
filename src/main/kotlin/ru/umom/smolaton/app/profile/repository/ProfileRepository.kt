package ru.umom.smolaton.app.profile.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.umom.smolaton.app.profile.entity.Profile
import ru.umom.smolaton.shared.enums.Interests


@Repository
interface ProfileRepository : JpaRepository<Profile, String> {
    @Query(
        """
    SELECT p FROM Profile p
    JOIN p.interests i
    WHERE i IN :interests
    AND p.id <> :profileId
    """
    )
    fun findUsersWithCommonInterests(
        @Param("interests") interests: List<Interests>,
        @Param("profileId") profileId: String
    ): List<Profile>
}
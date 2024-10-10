package ru.umom.smolaton.app.profile.dto

import ru.umom.smolaton.shared.enums.Interests
import ru.umom.smolaton.shared.enums.WantToFind


class GetProfileInfoRs(
    val userId: String,
    val userInterests: List<Interests>,
    val wantToFind: List<WantToFind>,
    val name: String,
    val surname: String,
    val avatarId: String
)

class CreateProfileInfoRq(
    val userInterests: MutableList<Interests>,
    val wantToFind: MutableList<WantToFind>,
    val name: String,
    val surname: String,
    val avatarId: String
)
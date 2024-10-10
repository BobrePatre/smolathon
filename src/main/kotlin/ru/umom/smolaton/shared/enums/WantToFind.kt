package ru.umom.smolaton.shared.enums

import com.fasterxml.jackson.annotation.JsonFormat


@JsonFormat(shape = JsonFormat.Shape.STRING)
enum class WantToFind {
    Work,
    Internship,
    Friends,
    Practice,
    Freelance,
}
package ru.umom.smolaton.shared.enums

import com.fasterxml.jackson.annotation.JsonFormat


@JsonFormat(shape = JsonFormat.Shape.STRING)
enum class Interests {
    Music,
    Sport,
    WebDesign,
    VideoEditing,
    Animation,
    Photo,
    Cinema,
    Coding,
}
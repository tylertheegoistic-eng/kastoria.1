package com.statecraft.kastoria.model

import kotlinx.serialization.Serializable

@Serializable
data class Minister(
    val id: String,
    val name: String,
    val portfolio: String,
    val ideology: String,
    val loyalty: Int = 60,
    val competence: Int = 60
)

@Serializable
data class MinisterRoster(val ministers: List<Minister>)

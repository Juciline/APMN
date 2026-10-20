package com.jucilinegabriel.modelo

import java.time.LocalDateTime

data class Sessao(val token: String, val dataHora: LocalDateTime, val utilizador: Utilizador)

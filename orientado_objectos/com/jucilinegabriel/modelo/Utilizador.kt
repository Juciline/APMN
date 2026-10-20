package com.jucilinegabriel.modelo

import com.jucilinegabriel.util.Hash

class Utilizador(val email: String, password: String) {
    private val passwordHash: String = Hash.sha256(password)
    val orcamento = Orcamento()
    val sessoes = mutableListOf<Sessao>()

    fun passwordCorrecta(password: String): Boolean = Hash.sha256(password) == passwordHash
}

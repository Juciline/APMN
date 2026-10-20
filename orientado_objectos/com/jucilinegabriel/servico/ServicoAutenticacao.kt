package com.jucilinegabriel.servico

import com.joaojdacosta.modelo.Sessao
import com.joaojdacosta.modelo.Utilizador
import com.joaojdacosta.util.Hash
import java.time.LocalDateTime
import java.util.UUID

class ServicoAutenticacao {
    // O email e a chave: um Map garante que nao ha duplicacao
    private val utilizadores = mutableMapOf<String, Utilizador>()

    fun iniciarSessao(email: String, password: String): Sessao? {
        val utilizador = utilizadores.getOrPut(email) { Utilizador(email, password) }
        if (!utilizador.passwordCorrecta(password)) return null
        val agora = LocalDateTime.now()
        val token = Hash.sha256("$email$agora${UUID.randomUUID()}")
        return Sessao(token, agora, utilizador).also { utilizador.sessoes.add(it) }
    }
}

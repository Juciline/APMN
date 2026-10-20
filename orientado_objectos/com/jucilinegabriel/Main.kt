package com.jucilinegabriel

import com.joaojdacosta.servico.ServicoAutenticacao
import com.joaojdacosta.ui.Interpretador

fun main() {
    val interpretador = Interpretador(ServicoAutenticacao())
    while (true) {
        val linha = readlnOrNull() ?: break
        interpretador.executar(linha)?.let { println(it) }
    }
}

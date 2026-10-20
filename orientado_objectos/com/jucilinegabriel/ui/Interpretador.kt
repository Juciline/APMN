package com.jucilinegabriel.ui

import com.joaojdacosta.modelo.Movimento
import com.joaojdacosta.modelo.Sessao
import com.joaojdacosta.modelo.TipoMovimento
import com.joaojdacosta.servico.ServicoAutenticacao

class Interpretador(private val autenticacao: ServicoAutenticacao) {
    private var sessao: Sessao? = null

    /** Executa uma linha de comando e devolve o texto a apresentar (ou null). */
    fun executar(linha: String): String? {
        val a = linha.trim().split(" ").filter { it.isNotEmpty() }
        val comando = a.firstOrNull() ?: return null
        if (comando == "u") return iniciarSessao(a)
        if (comando == "exit") {
            sessao = null
            return null
        }
        val orcamento = sessao?.utilizador?.orcamento ?: return "Sessao nao iniciada"
        return when (comando) {
            "i" -> {
                val m = Movimento(a[1].toInt(), TipoMovimento.deSimbolo(a[4]), a[5])
                orcamento.inserir(m, a[2].toInt(), a[3].toInt())
                null
            }
            "l" -> orcamento.listar(a[1].toInt())
                .mapIndexed { i, m -> "$i ${m.montante} ${m.tipo.simbolo} ${m.descricao}" }
                .joinToString("\n").ifEmpty { null }
            "r" -> { orcamento.remover(a[1].toInt(), a[2].toInt()); null }
            "b" -> orcamento.balanco(a[1].toInt()).toString()
            "m" -> { orcamento.valorMinimo = a[1].toInt(); null }
            "v" -> if (orcamento.valido()) "Valido" else "Invalido"
            "w" -> orcamento.primeiraDataComportavel(a[1].toInt(), a[2].toInt())?.toString()
                ?: "Incomportavel"
            else -> "Comando desconhecido"
        }
    }

    private fun iniciarSessao(a: List<String>): String? {
        sessao = autenticacao.iniciarSessao(a[1], a[2])
        return if (sessao == null) "Credenciais invalidas" else null
    }
}

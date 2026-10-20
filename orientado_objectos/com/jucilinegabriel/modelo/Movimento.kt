package com.jucilinegabriel.modelo

enum class TipoMovimento(val simbolo: Char) {
    RECEITA('r'), DESPESA('d');

    companion object {
        fun deSimbolo(c: String): TipoMovimento = if (c == "r") RECEITA else DESPESA
    }
}

data class Movimento(val montante: Int, val tipo: TipoMovimento, val descricao: String) {
    /** Valor com sinal: positivo para receitas e negativo para despesas. */
    val efeito: Int get() = if (tipo == TipoMovimento.RECEITA) montante else -montante
}

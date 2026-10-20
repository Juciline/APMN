package com.jucilinegabriel.modelo

class Orcamento {
    companion object {
        const val DATA_MAX = 999
    }

    private val movimentosPorData = Array(DATA_MAX + 1) { mutableListOf<Movimento>() }
    var valorMinimo: Int = 0

    fun inserir(m: Movimento, primeiraData: Int, periodicidade: Int) {
        var data = primeiraData
        while (data <= DATA_MAX) {
            movimentosPorData[data].add(m)
            if (periodicidade == 0) break
            data += periodicidade
        }
    }

    fun listar(data: Int): List<Movimento> = movimentosPorData[data].toList()

    fun remover(data: Int, indice: Int) {
        movimentosPorData[data].removeAt(indice)
    }

    /** Balanco acumulado de cada data entre 0 e DATA_MAX. */
    fun balancos(): IntArray {
        val b = IntArray(DATA_MAX + 1)
        var acumulado = 0
        for (d in 0..DATA_MAX) {
            acumulado += movimentosPorData[d].sumOf { it.efeito }
            b[d] = acumulado
        }
        return b
    }

    fun balanco(data: Int): Int = balancos()[data]

    fun valido(): Boolean = balancos().all { it >= valorMinimo }

    fun primeiraDataComportavel(dataInicial: Int, valor: Int): Int? {
        val b = balancos()
        val minimoAPartirDe = IntArray(DATA_MAX + 2) { Int.MAX_VALUE }
        for (d in DATA_MAX downTo 0) minimoAPartirDe[d] = minOf(b[d], minimoAPartirDe[d + 1])
        return (dataInicial..DATA_MAX).firstOrNull { minimoAPartirDe[it] - valor >= valorMinimo }
    }
}

package com.jucilinegabriel

const val DATA_MAX = 999

enum class Tipo { RECEITA, DESPESA }

data class Movimento(val montante: Int, val tipo: Tipo, val descricao: String)

// Estado global do programa procedimental
val movimentos = Array(DATA_MAX + 1) { mutableListOf<Movimento>() }
var valorMinimo = 0

fun inserir(a: List<String>): String? {
    val montante = a[1].toInt()
    val primeira = a[2].toInt()
    val periodo = a[3].toInt()
    val tipo = if (a[4] == "r") Tipo.RECEITA else Tipo.DESPESA
    val descricao = a[5]
    var data = primeira
    while (data <= DATA_MAX) {
        movimentos[data].add(Movimento(montante, tipo, descricao))
        if (periodo == 0) break
        data += periodo
    }
    return null
}

fun listar(a: List<String>): String? =
    movimentos[a[1].toInt()]
        .mapIndexed { i, m -> "$i ${m.montante} ${if (m.tipo == Tipo.RECEITA) 'r' else 'd'} ${m.descricao}" }
        .joinToString("\n")
        .ifEmpty { null }

fun remover(a: List<String>): String? {
    movimentos[a[1].toInt()].removeAt(a[2].toInt())
    return null
}

fun balancos(): IntArray {
    val b = IntArray(DATA_MAX + 1)
    var acc = 0
    for (d in 0..DATA_MAX) {
        for (m in movimentos[d]) acc += if (m.tipo == Tipo.RECEITA) m.montante else -m.montante
        b[d] = acc
    }
    return b
}

fun balanco(a: List<String>): String? = balancos()[a[1].toInt()].toString()

fun alterarMinimo(a: List<String>): String? {
    valorMinimo = a[1].toInt()
    return null
}

fun verificar(a: List<String>): String? =
    if (balancos().all { it >= valorMinimo }) "Valido" else "Invalido"

fun comportavel(a: List<String>): String? {
    val inicio = a[1].toInt()
    val valor = a[2].toInt()
    val b = balancos()
    // minimo dos balancos de d ate 999
    val minSufixo = IntArray(DATA_MAX + 2) { Int.MAX_VALUE }
    for (d in DATA_MAX downTo 0) minSufixo[d] = minOf(b[d], minSufixo[d + 1])
    for (d in inicio..DATA_MAX) {
        if (minSufixo[d] - valor >= valorMinimo) return d.toString()
    }
    return "Incomportavel"
}

fun executar(linha: String): String? {
    val a = linha.trim().split(" ").filter { it.isNotEmpty() }
    return when (a.firstOrNull()) {
        "i" -> inserir(a)
        "l" -> listar(a)
        "r" -> remover(a)
        "b" -> balanco(a)
        "m" -> alterarMinimo(a)
        "v" -> verificar(a)
        "w" -> comportavel(a)
        else -> "Comando desconhecido"
    }
}

fun main() {
    var cmd: String
    while (true) {
        cmd = readlnOrNull() ?: break
        val saida = executar(cmd)
        if (saida != null) println(saida)
    }
}

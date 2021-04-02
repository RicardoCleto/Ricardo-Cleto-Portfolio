fun buildMenu(): String {
    return "1-> Start New Game;\n0-> Exit Game;\n"
}

fun encortanIniciocinzento(countLinhas: Int, numLines: Int, showPieces: Boolean, startGrey: String, end: String): String {
    var frase = ""
    if (countLinhas == numLines && showPieces) {
        frase += "$startGrey \u2659 $end"
    } else if (countLinhas == 1 && showPieces) {
        frase += "$startGrey \u265F $end"
    } else {
        frase += "$startGrey   $end"
    }
    return frase

}

fun encortarInicioBranco(countLinhas: Int, numLines: Int, startWhite: String, showPieces: Boolean, end: String): String {
    var frase = ""
    if (countLinhas == numLines && showPieces) {
        frase += "$startWhite \u2659 $end"
    } else if (countLinhas == 1 && showPieces) {
        frase += "$startWhite \u265F $end"
    } else {
        frase += "$startWhite   $end"
    }
    return frase
}


fun encortarFimBranco(countLinhas: Int, numLines: Int, showPieces: Boolean, startWhite: String, end: String): String {
    var frase = ""
    if (countLinhas == numLines - 1 && showPieces) {
        frase += "$startWhite \u2659 $end"
    } else if (countLinhas == 0 && showPieces) {
        frase += "$startWhite \u265F $end"
    } else {
        frase += "$startWhite   $end"
    }
    return frase
}

fun encortarFimCinzento(countLinhas: Int, numLines: Int, showPieces: Boolean, startGrey: String, end: String): String {
    var frase = ""
    if (countLinhas == numLines - 1 && showPieces) {
        frase += "$startGrey \u2659 $end"
    } else if (countLinhas == 0 && showPieces) {
        frase += "$startGrey \u265F $end"
    } else {
        frase += "$startGrey   $end"
    }
    return frase
}

fun checkIsNumber(number: String): Boolean {
    return number.toIntOrNull() != null
}


fun checkName(nome: String): Boolean {
    var count = 0
    var espaco = 0

    while (count < nome.length - 1) {
        if (nome[count] == ' ') {
            espaco++
            if ((nome[0] in 'A'..'Z') && (nome[count + 1] in 'A'..'Z') && espaco == 1) {
                return true
            }
        }
        count++
    }
    return false
}

fun showChessLegendOrPieces(message: String): Boolean? {
    if (message == "y" || message == "Y") {
        return true
    }
    if (message == "n" || message == "N") {
        return false
    }
    return null
}

fun buildBoard(numColumns: Int, numLines: Int, showLegend: Boolean = false, showPieces: Boolean = false): String {
    val esc: String = Character.toString(27); val end = "$esc[0m"
    val startBlue = "$esc[30;44m"; val startGrey = "$esc[30;47m"
    val startWhite = "$esc[30;30m"; var countColunas: Int
    var countLinhas = 0; var frase = ""
    var letra = 'A'; var numeroB = '1'
    val azul = "$startBlue   $end"
    if (showLegend == true) {
        while (countLinhas < numLines + 2) {
            countColunas = 0
            if (countLinhas == 0) {
                while (countColunas < numColumns + 2) {
                    if (countColunas == 0 || countColunas == numColumns + 1) {
                        frase += azul
                    } else {
                        frase += "$startBlue $letra $end"
                        letra++ }
                    countColunas++ } }
            if (countLinhas in 1..numLines) { //+0
                if (countColunas == 0) {
                    frase += "$startBlue $numeroB $end"; numeroB++
                }
            }
            if (countLinhas == numLines + 1) {
                frase += azul
            }
            while (countColunas < numColumns) { // +0
                if (countLinhas == numLines+1) { // +1
                    frase += azul
                } else {
                    if (countColunas % 2 == 1) {
                        if (countLinhas % 2 == 0) { frase += encortarInicioBranco(countLinhas, numLines, startWhite, showPieces, end)
                        } else { frase += encortanIniciocinzento(countLinhas, numLines, showPieces, startGrey, end) } } else {
                        if (countLinhas % 2 == 1) { frase += encortarInicioBranco(countLinhas, numLines, startWhite, showPieces, end)
                        } else { frase += encortanIniciocinzento(countLinhas, numLines, showPieces, startGrey, end) } }
                    if (countColunas == numColumns - 1) {
                        frase += azul
                    }
                } ;countColunas++ }
            if(countColunas == numColumns && countLinhas==numLines+1){
                frase += "$startBlue   $end"}
            frase += "\n"
            countLinhas++ }
        return frase } else {
        while (countLinhas < numLines) {
            countColunas = 0
            while (countColunas < numColumns) {
                if (countColunas % 2 == 0) {
                    if (countLinhas % 2 == 0) {
                        frase += encortarFimBranco(countLinhas, numLines, showPieces, startWhite, end)
                    } else {
                        frase += encortarFimCinzento(countLinhas, numLines, showPieces, startGrey, end)
                    }
                } else {
                    if (countLinhas % 2 == 1) {
                        frase += encortarFimBranco(countLinhas, numLines, showPieces, startWhite, end)
                    } else {
                        frase += encortarFimCinzento(countLinhas, numLines, showPieces, startGrey, end)
                    }
                }
                countColunas++
            }
            frase += "\n"
            countLinhas++ }
        return frase } }
fun main() {
    println("Welcome to the Chess Board Game!")
    val respostaInvalida = "Invalid response."
    var error=true ; var menu: Int? = 1
    while (menu != 0) {
        println(buildMenu())
        menu = readLine()?.toIntOrNull()
        when (menu) {
            1 -> {
                var firstP = ""
                var secondP = ""

                while (!checkName(firstP)) {
                    println("First player name?\n")
                    firstP = readLine().toString()

                    if (!checkName(firstP)) println(respostaInvalida)
                }

                while (!checkName(secondP)) {
                    println("Second player name?\n")
                    secondP = readLine().toString()
                    if (!checkName(secondP)) {
                        println(respostaInvalida)
                    }
                }
                var numColumns = ""
                while (error) {
                    println("How many chess columns?\n")
                    numColumns = readLine().toString()
                    error=false
                    if (!checkIsNumber(numColumns) || numColumns.toInt() < 5) {
                        error=true
                        println(respostaInvalida)
                    }
                }
                error=true

                var numLines = ""
                while (error) {
                    println("How many chess lines?\n")
                    numLines = readLine().toString()
                    error=false
                    if (!checkIsNumber(numLines) || numLines.toInt() < 5) {
                        error=true
                        println(respostaInvalida)
                    }
                }
                error=true

                var showLegend = ""
                while (showChessLegendOrPieces(showLegend) == null) {
                    println("Show legend (y/n)?\n")
                    showLegend = readLine().toString()
                    if (showChessLegendOrPieces(showLegend) == null) {
                        println(respostaInvalida)
                    }
                }

                var showPieces = ""
                while (showChessLegendOrPieces(showPieces) == null) {
                    println("Show pieces (y/n)?\n")
                    showPieces = readLine().toString()
                    if (showChessLegendOrPieces(showPieces) == null) {
                        println(respostaInvalida)
                    }
                }
                val nullOuNao = showChessLegendOrPieces(showLegend) ?: false
                val nullOuNao1 = showChessLegendOrPieces(showPieces) ?: false
                println(buildBoard(numColumns.toInt(), numLines.toInt(), nullOuNao, nullOuNao1))
            }
            0 -> return
        }
    }
}
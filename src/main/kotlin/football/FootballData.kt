package football

import common.IntegerMinimizer
import java.io.File

fun main(args : Array<String>) {
    val lines = File("src/main/resources/football.dat").readLines()
    val minGoalDifferential = IntegerMinimizer(
            ::looksLikeAFootballRecord,
            ::toFootballRecord,
            FootballRecord::goalDifferential)
            .findMin(lines)
    println(minGoalDifferential?.teamName)
}

fun looksLikeAFootballRecord(raw: String): Boolean {
    return Regex("""^\s+\d+\..*$""").matches(raw)
}

fun toFootballRecord(raw : String) : FootballRecord {
    val pieces = Regex("""\s+""")
            .split(raw)
            .filter { piece -> piece.length > 0 }
    return FootballRecord(
            pieces[1],
            pieces[6].toInt(),
            pieces[8].toInt())
}

data class FootballRecord(
        val teamName: String,
        val goalsFor: Int,
        val goalsAgainst: Int) {

    fun goalDifferential() : Int {
        return Math.abs(goalsFor - goalsAgainst)
    }
}

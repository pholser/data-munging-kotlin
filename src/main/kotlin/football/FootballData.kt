package football

import common.SpreadMinimizer
import common.SpreadRecord
import java.io.File

fun main(args : Array<String>) {
    val lines = File("src/main/resources/football.dat").readLines()
    val minGoalDifferential = SpreadMinimizer(
            ::looksLikeAFootballRecord,
            ::toFootballRecord)
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
        val goalsAgainst: Int) : SpreadRecord {

    override fun key() : String {
        return teamName
    }
    override fun spread() : Int {
        return Math.abs(goalsFor - goalsAgainst)
    }
}

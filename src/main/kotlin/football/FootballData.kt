package football

import java.io.File

fun main(args : Array<String>) {
    val lines = File("src/main/resources/football.dat").readLines()
    val footballRecords =
            lines.filter(::looksLikeAFootballRecord)
                    .map(::toFootballRecord)
    println(footballRecords.maxBy(FootballRecord::spread)?.teamName)
}

fun looksLikeAFootballRecord(raw: String): Boolean {
    return Regex("""^\s+\d+\..*$""").matches(raw)
}

fun toFootballRecord(raw : String) : FootballRecord {
    val pieces = Regex("""\s+""")
            .split(raw)
            .filter { piece -> piece.length > 0 }
            .map(::sanitize)
    return FootballRecord(
            pieces[1],
            pieces[6].toInt(),
            pieces[8].toInt())
}

fun sanitize(piece: String): String {
    return piece
}

data class FootballRecord(
        val teamName: String,
        val goalsFor: Int,
        val goalsAgainst: Int) {

    fun spread() : Int {
        return goalsFor - goalsAgainst
    }
}

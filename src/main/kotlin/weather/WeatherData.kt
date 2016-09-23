package weather

import common.SpreadMinimizer
import common.SpreadRecord
import java.io.File

fun main(args : Array<String>) {
    val lines = File("src/main/resources/weather.dat").readLines()
    val minTemperatureDifferential = SpreadMinimizer(
                ::looksLikeAWeatherRecord,
                :: toWeatherRecord)
            .findMin(lines)
    println(minTemperatureDifferential?.day)
}

fun looksLikeAWeatherRecord(raw: String): Boolean {
    return Regex("""^\s+\d.*$""").matches(raw)
}

fun toWeatherRecord(raw : String) : WeatherRecord {
    val pieces = Regex("""\s+""")
            .split(raw)
            .filter { piece -> piece.length > 0 }
            .map(::sanitize)
    return WeatherRecord(
            pieces[0].toInt(),
            pieces[1].toInt(),
            pieces[2].toInt())
}

fun sanitize(piece: String): String {
    return piece.replace(Regex("""\D"""), "")
}

data class WeatherRecord(
        val day: Int,
        val maxTemp: Int,
        val minTemp: Int) : SpreadRecord {

    override fun key(): String {
        return day.toString()
    }
    override fun spread() : Int {
        return Math.abs(maxTemp - minTemp)
    }
}

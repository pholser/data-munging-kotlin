import java.io.File

fun main(args : Array<String>) {
    val lines = File("src/main/resources/weather.dat").readLines()
}

fun toWeatherRecord(raw : String) : WeatherRecord {
    val pieces = Regex("\\s+").split(raw)
    return WeatherRecord(pieces[0].toInt(), pieces[1].toInt(), pieces[2].toInt())
}

data class WeatherRecord(
        val day: Int,
        val maxTemp: Int,
        val minTemp: Int)

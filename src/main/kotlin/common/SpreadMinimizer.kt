package common

class SpreadMinimizer<T> (
      val matcher: (String) -> Boolean,
      val mapper: (String) -> T)
      where T : SpreadRecord
{
    fun findMin(raw : List<String>) : T? {
        return raw.filter(matcher).map(mapper).minBy { r -> r.spread() }
    }
}

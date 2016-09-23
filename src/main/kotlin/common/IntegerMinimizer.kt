package common

class IntegerMinimizer<T> (
      val matcher: (String) -> Boolean,
      val mapper: (String) -> T,
      val metric : (T) -> Int)
{
    fun findMin(raw : List<String>) : T? {
        return raw.filter(matcher).map(mapper).minBy(metric)
    }
}

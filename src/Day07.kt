import kotlin.math.roundToInt

fun main() = day(7) {
    fun median(l: List<Int>) = l.sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }

    fun resolveInput() = inputString.split(',').map { it.toInt() }

    part1 {
        val median = median(resolveInput())
        resolveInput().sumOf { if (it > median) it - median else median - it }
    }

    part2 {
        val avg = resolveInput().average().also { println(it) }.roundToInt()
        resolveInput().sumOf { ((if (it > avg) it - avg else avg - it) downTo 1).sum() }
    }

    expectPart1 = 37
    expectPart2 = 168
}
